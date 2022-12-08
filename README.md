#idrivewebautomation

Contains Automation code of www.idrive.com with page object model framework using TestNG. The project has developed to be compatibile with java 1.8 & above, and selenium 4.1.2


#Softwares to be used:

- Eclipse Photon
- TestNG
- GIT Plugin 

#Important project setup steps:

1. To install TestNG : beust.com/eclipse-old/eclipse-6.7.0.20120808_0858

2. To ensure google drive jar works:

	lib : This folder should be present parallel to src and contain the google drive jar, which has to be installed. 
	Follow below installation steps:
	> Navigate to the lib folder on command prompt and run the below command
	mvn install:install-file -Dfile=com.AccessGoogleDrive-0.0.1-SNAPSHOT.jar -DgroupId=com.AccessGoogleDrive -DartifactId=AccessGoogleDrive -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar
 
#Below is the directory structure of the project:

Under Main:
- Page Library: Contains all the pages which contains the functions to build the tests.  
- TestBase: This contains all the functions that is globally used in the project(Loading of properties files, Webdriver creation, Wait creation etc). 
- Utilities: Contains some of the helper utilities such as date and time, email sending etc which aid in the project script creation. 
- WebFunctionalities: This contains reusable class WebFunctioality which handles the functions required for the script development. This will not change further.

Under Test:
- resources: This folder contains the secrets file that will authenticate the google sheets API
- scripts: This contains all the test scripts written for RemotePC web site.

#Upload path for upload file test
- The file to be used for Upload test should be placed on desktop, The path should be provided in the constants.