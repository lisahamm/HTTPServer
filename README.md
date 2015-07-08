# Java HTTP Server

Simple HTTP server written in Java to meet the requirements of [CobSpec] (https://github.com/8thlight/cob_spec). 

### Requirements

JDK 8

### Installation

Clone the git repository 

    $ git clone https://github.com/lisahamm/HTTPServer
    
Then `cd` into the directory

    $ cd HTTPServer
    
Build the JAR using gradle wrapper

    $ ./gradlew build
    
### Run the Server

Start the server from the command line 

    $ java -jar build/libs/HTTPServer.jar -p PORT -d PATH/TO/PUBLIC/DIRECTORY

### Run the tests

Run the tests from the root directory 

    $ ./gradlew check

    