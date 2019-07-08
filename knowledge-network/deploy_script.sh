#! /bin/bash
set -e

#######################################################################
# Path
#######################################################################
PROJECT_NAME="knowledge-network"
PROJECT_HOME="$(pwd)"
PROJECT_TARGET="$PROJECT_HOME/target"

SERVER_IP="222.200.177.110"
SERVER_USER_NAME="vinzor"
SERVER_USER_PWD="admin123"
SERVER_TOMCAT_HOME="/home/$SERVER_USER_NAME/apache-tomcat-8.5.11"

JDK_HOME="/home/$SERVER_USER_NAME/jdk1.8.0_60"
JRE_HOME="$JDK_HOME/jre"

TOMCAT_SHUTDOWN_SCRIPT="$SERVER_TOMCAT_HOME/bin/shutdown.sh"
TOMCAT_STARTUP_SCRIPT="$SERVER_TOMCAT_HOME/bin/startup.sh"

#######################################################################
# Declare some common method
#######################################################################
function error()
{
  echo "[ERROR] ------------------------------------------------------------------------"
  echo "[ERROR] $1"
  echo "[ERROR] ------------------------------------------------------------------------"
  exit 1
}

function info()
{
  echo "[INFO] ------------------------------------------------------------------------"
  echo "[INFO] $1"
  echo "[INFO] ------------------------------------------------------------------------"
}

##################################################
# Maven
##################################################
mvn clean install
mvn package

##################################################
# Deploy war to the server
##################################################
if [ ! -x $PROJECT_TARGET ]; then
  error 'No target folder!'
fi

cd $PROJECT_TARGET
# GET the war
war=$(ls -l | grep -i "$PROJECT_NAME.war" | awk '{ print $9 }')
# File not exist
if [ -z "$war" ]; then
  error 'Cannot find the war!'
fi

# Copy to the server's tomcat
sshpass -p "$SERVER_USER_PWD" scp $war $SERVER_USER_NAME@$SERVER_IP:$SERVER_TOMCAT_HOME/webapps/

# Restart tomcat
info 'Restarting Tomcat...'

sshpass -p "$SERVER_USER_PWD" ssh $SERVER_USER_NAME@$SERVER_IP "bash $TOMCAT_SHUTDOWN_SCRIPT"
sshpass -p "$SERVER_USER_PWD" ssh $SERVER_USER_NAME@$SERVER_IP "bash $TOMCAT_STARTUP_SCRIPT"

info 'SUCCEED!'

