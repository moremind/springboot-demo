DATE=$(date +%Y-%m-%d)
JAVA_HOME=/opt/java/jdk1.8.0_181
CLASSPATH=$JAVA_HOME/lib/
PATH=$PATH:$JAVA_HOME/bin
export PATH JAVA_HOME CLASSPATH
#应用所在目录
DIR=/home/xx/
#应用日志所在目录
LOGDIR=/home/xxx/logs/
#日志文件名
LOGFILE=xxx-info
#启动Jar包名称
JARFILE=xxx.jar
#Jar全路径
JARPATH=$DIR/xxx.jar
cd $DIR
jps
ps -ef | grep $JARFILE | grep -v grep | awk '{print $2}' | xargs kill -9
nohup java -jar $JARPATH --spring.profiles.active=pro > /dev/null &
if [ $? = 0 ];then
        sleep 15
        tail -n 50 $LOGDIR/$LOGFILE-$DATE.log
fi
jps
