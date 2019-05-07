Agoda - Password check functionality (Windows Platform)
Developed by - Ayush Goel

Steps to run the code:
1. Using Command Line:
Pre-requisites - 
* Install JDK (Preferably > Java 7)
* Download Maven Zip file (Attached separately)
* Unzip maven and add <Path to Maven>/bin in the 'Path' environment variable

Run tests - 
* Clone project from Bitbucket repository
* Open command prompt and navigate inside the cloned project directory
* Type 'mvn install' to resolve maven dependencies
* Type 'mvn test' to run tests (Tests should run using 'mvn install'. Use this command in case tests don't run)
* Verify output on Command prompt

2. Using Eclipse IDE:
* Clone project from Bitbucket repository
* Import Maven project in Eclipse
* Wait till Eclipse resolve the dependencies
* Right click on 'testng.xml' and run as TestNg tests
* Verify output on Console or verify using /test-output/index.html

Project summary:
Class - Password.java
Public function - changePassword (This will return true/false if password can be changed)

'changePassword' will return true if:
* New password passes all password requirements
* New and old password are less than 80% similar

Test case data is provided using TestNg's 'dataProvider' method. In order to test this application with different test data, please PasswordTest.java and add test data in String array.

Password similarity:
Password can be considered similar when a sequence of characters is present in the old password is also present in the new password. We can have a solution of this problem in following ways:
* Checking the availability of sequences of characters of new password in old password string and vice versa. Sequence occurrence should be less than 80% of max length of string (new or old) - This algorithm is applied in the code.
* Levenstein algorithm which will let us know the number of character substitutions required to change one string to another. Character substitutions to old password length should be more than 20% (to make new password less than 80% similar) - This algorithm is not applied in the code.

Important Note - Console logging is done in order to make tests more readable. However, this should be avoided to make framework light.
