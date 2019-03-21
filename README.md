# Installing and Running the Brightspot Vagrant

## Prerequisites

You will need both [Vagrant](https://www.vagrantup.com/) and
[VirtualBox](https://www.virtualbox.org/) installed.

## One-Time Setup

Create a global Vagrantfile at `~/.vagrant.d/Vagrantfile`. If you do not have
one already, the skeleton for the file looks like this:

```ruby
# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure('2') do |config|
  # configuration goes here...
end
```

### Mac

#### Mounted Folders

Add the following snippet to your global Vagrantfile to mount your Mac user home
directory inside the Vagrant. Assuming your code also lives in your home
directory this makes it easy to access the source from within your Vagrant and
ensures that the Dari Reloader also works properly.

```ruby
home = File.expand_path('~')
config.vm.synced_folder home, home, type: 'nfs'
```

Ensure you have the `vagrant-vbguest` plugin installed so that the appropriate
Guest Additions versions is installed when you boot the box which will ensure
that the mounted folder configured above works properly.

```bash
vagrant plugin install vagrant-vbguest
```

## Download & Run

Create a folder where you plan to install and run the box from. The folder name
will end up being the VirtualBox instance name by default, unless the folder
is within a git repository sub-tree in which case the directory name of the
git repo root will be used instead. If you are installing multiple instances
it is important that the name be unique otherwise VirtualBox will throw an
error. You can always manually change the VirtualBox name by modifying the
Vagrantfile but is unnecessary if a good unique folder name is created outright.

Once the folder is created, you will need to obtain a Vagrantfile. You can run
the following command from within your newly created folder to download the
Vagrantfile.

```bash
curl -o Vagrantfile https://s3.amazonaws.com/brightspot-vagrant/boxes/Vagrantfile
```

From within the folder containing the Vagrantfile, run `vagrant up` to boot the
machine. This might take a while the first time since it has to download the
box from S3. Once the machine is booted, you can SSH in by running `vagrant ssh`.

To get the IP address of the Vagrant, run `hostname -I` while logged into the VM.

If you see an error message like the following:

```bash
==> default: Error downloading: An error occurred while downloading the remote file. The error
==> default: message, if any, is reproduced below. Please fix this error and try
==> default: again.
==> default: 
==> default: The requested URL returned error: 403 Forbidden
```

This just means there isn't a box file that matches your version of VirtualBox,
but a generic fallback box will be downloaded instead, and you can safely ignore
it.

## Services

The following services come pre-installed:

* Apache (w/ DIMS)
* MySQL
* Tomcat (w/ Solr)
* Tomcat (w/ Brightspot)
* Greenmail
* Varnish

The most common service commands are to stop, start and restart a service:

```
service <service-name> (stop|start|restart)
```

#### Apache (w/ DIMS)

* Service Name: `apache2`
* Installation Directory: `/etc/apache2`
* Port(s): 80, 443

#### MySQL

* Service Name: `mysql`
* Installation Directory: `/var/lib/mysql`
* Port: 3306

#### Tomcat (w/ Solr)

* Service Name: `solr`
* Installation Directory: `/var/lib/solr`
* Tomcat Directory: `/servers/solr`
* Port: 8180

#### Tomcat (w/ Brightspot)

* Service Name: `brightspot`
* Tomcat Directory: `/servers/brightspot`
* Port: 8080

#### Greenmail

* Service Name: `greenmail`
* Log Directory: `/servers/greenmail/logs`
* Port(s): IMAP:3143, SMTP:3025, POP3:3110, IMAPs:3993, SMTPs:3465, POP3s:3995

#### Varnish

* Service Name: `varnish`
* Installation Directory: `/etc/varnish`
* Port: 6081, 6082 (Management Interface)
