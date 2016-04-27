# SimpleNetworkReportingTool

The purpose of the program is to be able to see all processes and have the ability to kill a process on a computer connected to the same network as the server that's running it. 

Query on the server will run a script to login (after prompting you to type the username and password for the remote computer) to the remote and report the processes to the server. Upon clicking the task in the GUI and clicking end task it will send a script to the remote computer and kill that task.

To run and compile the program:
For windows (below):
 - hit the key combination windows key+r to bring up the command prompt
 - to check the java version; type java -version (required version: 7)
 - type javac [yourdownloadpath]\SNRT
 - type java [yourcompilepath]\SRNT.java

For OSX and Linux:
 - open terminal
 - type javac [yourdownloadpath]\SNRT
 - type java [yourcompilepath]\SRNT.java

This tool can only run on the java runtime enviroment version 7 and above
