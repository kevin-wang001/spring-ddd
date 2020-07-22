#!/bin/bash
cd `dirname $0`
cd ..
DEPLOY_DIR=`pwd`
JAR_NAME=$DEPLOY_DIR/lib/fengxiaoadmin.application.jar
nohup java -Xmx2G -Xms2G -XX:MaxMetaspaceSize=512M -XX:MetaspaceSize=512M -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -XX:+ParallelRefProcEnabled -XX:ErrorFile=$DEPLOY_DIR/console/hs_err_pid%p.log  -XX:HeapDumpPath=$DEPLOY_DIR/console -XX:+HeapDumpOnOutOfMemoryError -jar $JAR_NAME>/dev/null 2>&1 &
