FROM brightspot/tomcat:8-jdk8
USER root
COPY context.xml.erb /servers/tomcat/conf/context.xml.erb
COPY entrypoint.sh /

RUN rm -rf /servers/tomcat/webapps/_reloader && \
    wget -qO- https://artifactory.psdops.com/public/com/psddev/dari-reloader-tomcat/4.2.26-x6f1895/dari-reloader-tomcat-4.2.26-x6f1895.war > /servers/tomcat/webapps/_reloader.war && \
    mkdir -p /servers/tomcat/webapps/_reloader && \
    cd /servers/tomcat/webapps/_reloader && \
    jar xf /servers/tomcat/webapps/_reloader.war && \
    rm /servers/tomcat/webapps/_reloader.war && \
    chown -R tomcat:tomcat /servers/tomcat

USER tomcat
