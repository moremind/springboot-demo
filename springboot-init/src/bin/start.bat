@echo off
rem ======================================================================
rem windows startup script
rem
rem ======================================================================


rem startup jar
@echo off
set path=C:\Program Files\Java\jre1.8.0_201\bin
@echo %path%
java -jar -Xms128m -Xmx512m ../boot/xxx.jar

pause