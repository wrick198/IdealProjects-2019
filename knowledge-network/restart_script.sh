#! /bin/bash

#######################################################################
# Path
#######################################################################
SERVER_IP="222.200.177.110"
SERVER_USER_NAME="vinzor"
SERVER_USER_PWD="admin123"
SERVER_TOMCAT_HOME="/home/$SERVER_USER_NAME/apache-tomcat-8.5.11"

TOMCAT_RESTART_SCRIPT="$SERVER_TOMCAT_HOME/bin/restart_tomcat.sh"

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

#######################################################################
# Restart tomcat
#######################################################################
info 'Restarting Tomcat...'

sshpass -p "$SERVER_USER_PWD" ssh $SERVER_USER_NAME@$SERVER_IP "bash $TOMCAT_RESTART_SCRIPT"

info 'SUCCEED!'
