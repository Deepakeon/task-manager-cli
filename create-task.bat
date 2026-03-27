@echo off

REM ==========================
REM Starting CLI Tests
REM ==========================
echo Starting Cli Tests...
echo ------------------------------------------------

REM Compile all Java files
powershell -Command "javac -d out (Get-ChildItem -Recurse -Filter *.java src | %% { $_.FullName })"

REM Run test
REM echo.
REM echo Command: java -cp out TaskCli add "Learn Streams"
REM java -cp out TaskCli add "Learn Streams"


REM Run test
REM echo.
REM echo Command: java -cp out TaskCli update 1 "updated"
REM java -cp out TaskCli update 10 "updated"


REM echo.
REM echo Command: java -cp out TaskCli delete 1
REM java -cp out TaskCli delete 1

REM echo.
REM echo Command: java -cp out TaskCli mark-in-progress 1
REM java -cp out TaskCli mark-done 1

echo.
echo Command: java -cp out TaskCli list done
java -cp out TaskCli add --description "Deepak"