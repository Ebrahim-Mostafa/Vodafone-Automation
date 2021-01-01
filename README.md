## `Vodafone Automation Testing Task Installation` . 
<br />

## `Prerequisites:`

Download and install [Java 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html).<br />

Download and install [Apache Maven](https://maven.apache.org/download.cgi).<br />

## `ATUTestRecorder Installation:`

Please run this command on your console inside or outside the IDE considering the location of the JAR file which is in the libs folder:<br />

> mvn install:install-file -Dfile={Path}\ATUTestRecorder_2.1.jar -DgroupId=org.aut.tm -DartifactId=cxf-bundle -Dversion=2.1 -Dpackaging=jar <br />

In case you have a problem with the dependency, to resolve the issue please check the dependencies in this link and follow the same tags:<br /><br />

the dependency after adding the missing tags:<br />

=========================== <br />
```xml
<dependency>
<groupId>atu.testng.reports.ATUTestRecorder</groupId>
<artifactId>ATUTestRecorder</artifactId>
<version>2.1</version> 
</dependency>
```
============================<br />

## `MonteScreenRecorder Installation:`

Please run this command on your console inside or outside the IDE considering the location of the JAR file which is in the libs folder:<br />

> mvn install:install-file -Dfile={Path}\monte-screen-recorder-0.7.7.0.jar -DgroupId=com.github.stephenc.monte -DartifactId=monte-screen-recorder -Dversion=0.7.7.0 -Dpackaging=jar -DgeneratePom=true <br />

============================<br />

## `OWASP ZAP Installation:`
Please run these commands on your console inside or outside the IDE considering the location of the JAR file which is in the libs folder:<br />

> mvn install:install-file -Dfile={Path}\libs\zap-api-2.4-v6.jar -DgroupId=net.continuumsecurity -DartifactId=zap-java-api -Dversion=2.4.2  -Dpackaging=jar -DgeneratePom=true <br />

> mvn install:install-file -Dfile={Path}\libs\proxy-2.4.2-SNAPSHOT.jar -DgroupId=org.owasp -DartifactId=zaproxy-client-api -Dversion=2.4-6  -Dpackaging=jar -DgeneratePom=true <br />

> mvn install:install-file -Dfile={Path}\libs\harlib-1.1.1.jar -DgroupId=edu.umass.cs.benchlab -DartifactId=harlib -Dversion=1.1.1  -Dpackaging=jar -DgeneratePom=true <br />

## `OWASP ZAP Docker Container Setup:`
Insure that you are having Docker installed on your machine , if you haven't try to install it from here [Docker Hub](https://hub.docker.com/editions/community/docker-ce-desktop-windows/).<br />


## 1.Install or pull docker image -<br />
> docker pull owasp/zap2docker-stable <br />

## 2.You can start the ZAP in headless mode with following command - <br />
> docker run -u zap -p 8080:8080 -i owasp/zap2docker-stable zap.sh -daemon -host 0.0.0.0 -port 8080 -config api.addrs.addr.name=.* -config api.addrs.addr.regex=true -config api.disablekey=true <br />

## 3.Run your Selenium tests independently but within selenium tests configure the proxy to hit port 8080 (or whichever you are using). 
<br />

## 4.The following command will generate report in the container -
> docker exec $container_Id zap-cli report -o vulnerability.html -f HTML <br />

## 5.you can copy this report anywhere. Then stop the container and remove it.
============================<br />

## `Install Allure to generate reports:`

Open your windows PowerShell:<br />

One of the easiest way to start PowerShell in Windows is using Run window. Press Win + R keys on your keyboard, then type PowerShell and press Enter or click OK.<br />

Run these Commands:<br />

> Set-ExecutionPolicy RemoteSigned -scope CurrentUser <br />

> iex (new-object net.webclient).downloadstring('https://get.scoop.sh') <br />

In the above case, it was already installed, so you a message as 'Scoop is already installed'. You can run 'scoop update' to get the latest version any time, To install Allure, using Scoop, run the below command:<br />

> scoop install allure <br />