1. login ssh
2. deploy jar -> springToolSuite -> run -> Maven Install
3. upload jar len server > /Project/SoapService
4. command stop server: ps -aux | grep java
5. kill -9 pid
6. cd /Project/SoapService
6. nohup java -jar goijar.jar > std.out & > pid
