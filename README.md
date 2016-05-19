# SimpleNetworkReportingTool

The purpose of this program is to be able to see all processes and have the ability to kill a process on a computer connected to the same network as the server that's running the program.

Query on the server will run a script to login (after prompting you to type the username and password for the remote computer) to the remote computer, allowing you to view it's tasks/processes and killing any of those tasks/processes by highlighting/selecting the task/process and pressing the kill task button.

Although, the above is the purpose of this program it is now only functioning as a spiffy task-manager with a built in search function. In the future, I hope to enhance it by batch process/task killing, maybe put in some spiffy network graphs, and etc. Ultimately, I hope to make this program functional for multiple computers within a network and even make the program mobile ready.

I have a topic on the Cplusplus forum about this project, if anyone is curious.

Here is link: http://www.cplusplus.com/forum/lounge/178565/

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
