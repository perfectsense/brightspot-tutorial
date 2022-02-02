#!/bin/bash

set -e

# Symlink ROOT.war
if [[ ! -z "$ROOT_WAR" ]]
then
    if [[ "$ROOT_WAR" -nt /servers/tomcat/webapps/ROOT ]]
    then
        rm -rf /servers/tomcat/webapps/ROOT
    fi

    ln -svf $ROOT_WAR /servers/tomcat/webapps/ROOT.war
fi

# Generate initial context.xml/server.xml (or other templates as defined in /etc/docker/templates)
/usr/local/bin/container-template.rb

# Ensure the storage volume is owned by tomcat
sudo chown tomcat:tomcat /servers/tomcat/storage

# Add CONTEXT_PROPERTIES file contents to context.xml
if [[ ! -z "$CONTEXT_PROPERTIES" && -f "$CONTEXT_PROPERTIES" ]]
then
    cat $CONTEXT_PROPERTIES \
        | sed 's/[[:space:]]\+=[[:space:]]\+/=/g; s/^[[:space:]]\+//; s/[[:space:]]\+$//' \
        | grep -v '^#' \
        | grep -v '^$' \
    | while IFS='=' read -r k v; do
       echo "[$k] = [$v]"
       add_context_config "$k" "$v"
    done
fi

# Add CONTEXT_PROPERTIES_OVERRIDES file contents to context.xml
if [[ ! -z "$CONTEXT_PROPERTIES_OVERRIDES" && -f "$CONTEXT_PROPERTIES_OVERRIDES" ]]
then
    cat $CONTEXT_PROPERTIES_OVERRIDES \
        | sed 's/[[:space:]]\+=[[:space:]]\+/=/g; s/^[[:space:]]\+//; s/[[:space:]]\+$//' \
        | grep -v '^#' \
        | grep -v '^$' \
    | while IFS='=' read -r k v; do
       echo "[$k] = [$v]"
       add_context_config "$k" "$v"
    done
fi

# Append LOGGING_PROPERTIES to logging.properties
if [[ ! -z "$LOGGING_PROPERTIES" && -f "$LOGGING_PROPERTIES" ]]
then
    cat $LOGGING_PROPERTIES >> /servers/tomcat/conf/logging.properties
fi

# Run custom initialization script
if [[ ! -z "$INIT_SH" && -x "$INIT_SH" ]]
then
    $INIT_SH
fi

# Forward :80 to apache:80 so brightspot can detect that _reloader is installed and fetch DIMS URLs from itself
sudo socat TCP4-LISTEN:80,fork TCP4:apache:80 &

wait-for-it.sh ${MYSQL_HOST}:${MYSQL_PORT} -t 0 -- \
    wait-for-it.sh ${SOLR_HOST}:${SOLR_PORT} -t 0 -- \
    /servers/tomcat/bin/catalina.sh run
