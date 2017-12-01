#!/bin/bash

DAY=`date +%Y-%m-%d`

#the key word for searching the process id
PID_MSG=/usr/local/tomcat-account

PID_MSG_DATA=/data/fx/web/tomcat-account/webapps

#the path for deleting the log and tmp
CLEAN_PATH=$PID_MSG/work/Catalina

#msg of the file which is running now,backup it.
APP_PATH=$PID_MSG_DATA
APP_FILE_PATH=$PID_MSG_DATA/ROOT

#msg of the start file which is used to start the system
APP_START_FILE=startup.sh
APP_START_PATH=$PID_MSG/bin

#msg of the shutdown file which is used to shutdown the system
APP_SHUTDOWN_FILE=shutdown.sh
APP_SHUTDOWN_PATH=$PID_MSG/bin

#msg of the update file
BUILD_FILE_NAME=ROOT.war
BUILD_FILE_PATH=/root/upload/war

#msg of the old files for backup
OLD_FILE_PATH=/data/backup/program/web/$DAY/old_files

#path of the shell script log
SHELL_LOG_PATH=/data/backup/program/web/$DAY/auto_deploy



export JAVA_HOME="/data/jdk1.7.0_79"
mkdir -p /data/backup/program/web/
mkdir -p /data/backup/program/web/$DAY
mkdir -p /data/backup/program/web/$DAY/auto_deploy
mkdir -p /data/backup/program/web/$DAY/old_files

echo '=============================begin==================================='>>$SHELL_LOG_PATH/logs
tail -f $SHELL_LOG_PATH/logs &
#备份现有的环境，具体的备份路径和范围由个人决定
if [ -d "$APP_FILE_PATH" ]
then
    time_now_3=`date +%Y-%m-%d" "%H:%M:%S`
	echo $time_now_3 'Begin to backup the files: '$APP_FILE_PATH>>$SHELL_LOG_PATH/logs
	cp -r $APP_FILE_PATH* $OLD_FILE_PATH
	time_now_4=`date +%Y-%m-%d" "%H:%M:%S`
	echo $time_now_4 'End to backup the files.....'>>$SHELL_LOG_PATH/logs
fi

#关闭正在运行的系统
pid0=`ps -ef|grep $PID_MSG|grep -v grep|awk '{print $2}'`
if [ -n "$pid0" ]
then
	time_now_00=`date +%Y-%m-%d" "%H:%M:%S`
	echo $time_now_00 'Begin to shutdown the system....'>>$SHELL_LOG_PATH/logs
	echo 'Shutdown File is: ' $APP_SHUTDOWN_PATH/$APP_SHUTDOWN_FILE>>$SHELL_LOG_PATH/logs
	sh $APP_SHUTDOWN_PATH/$APP_SHUTDOWN_FILE
	time_now_001=`date +%Y-%m-%d" "%H:%M:%S`
	echo $time_now_001 'End to shutdown the system....'>>$SHELL_LOG_PATH/logs
	sleep 20
fi


pid=`ps -ef|grep $PID_MSG|grep -v grep|awk '{print $2}'`
if [ -n "$pid" ]
then
	time_now_1=`date +%Y-%m-%d" "%H:%M:%S`
	echo $time_now_1 'Begin to kill the progress.....'>>$SHELL_LOG_PATH/logs
	echo 'The pid: ' $pid ' will be killed....'>>$SHELL_LOG_PATH/logs
	kill -9 $pid
	time_now_2=`date +%Y-%m-%d" "%H:%M:%S`
	echo $time_now_2 'End to kill the progress.....'>>$SHELL_LOG_PATH/logs
fi



#备份完之后先删除掉现有的环境，备份哪些就删除哪些，主要是怕覆盖的时候有文件错误
time_now_5=`date +%Y-%m-%d" "%H:%M:%S`
echo $time_now_5 'Begin to delete the files'
if [ -d "$CLEAN_PATH" ]
then
	rm -rf $CLEAN_PATH
	echo 'delete : ' $CLEAN_PATH>>$SHELL_LOG_PATH/logs
fi
if [ -d "$APP_FILE_PATH" ]
then
	rm -rf $APP_FILE_PATH*
	echo 'delete : ' $APP_FILE_PATH>>$SHELL_LOG_PATH/logs
fi
time_now_6=`date +%Y-%m-%d" "%H:%M:%S`
echo $time_now_6 'End to delete the files....'>>$SHELL_LOG_PATH/logs

#把最新build的文件拷贝到需要解压的目录
warfile_00=$BUILD_FILE_PATH/$BUILD_FILE_NAME
if [ -f "$warfile_00" ]
then
	time_now_7=`date +%Y-%m-%d" "%H:%M:%S`
	echo $time_now_7 'Begin to copy the build files: ' $warfile_00>>$SHELL_LOG_PATH/logs
	cp -r $warfile_00 $APP_PATH
	time_now_8=`date +%Y-%m-%d" "%H:%M:%S`
	echo $time_now_8 'End to copy the build files.....'>>$SHELL_LOG_PATH/logs
fi


#重启系统
start_sh_00=$APP_START_PATH/$APP_START_FILE
if [ -f "$warfile_00" ]
then
	time_now_13=`date +%Y-%m-%d" "%H:%M:%S`
	echo $time_now_13 'Begin to restart the system....'>>$SHELL_LOG_PATH/logs
	echo 'Start File is: ' $start_sh_00>>$SHELL_LOG_PATH/logs
	sh $start_sh_00
	time_now_14=`date +%Y-%m-%d" "%H:%M:%S`
	echo $time_now_14 'End to restart the system....'>>$SHELL_LOG_PATH/logs
fi
echo '=============================end==================================='>>$SHELL_LOG_PATH/logs

#关闭 tail 后台进程
tail_str="$SHELL_LOG_PATH/logs"
tail_ps=`ps -ef|grep $tail_str|grep -v grep|awk '{print $2}'`
if [ -n "$tail_ps" ]
then
  kill -9 $tail_ps
fi