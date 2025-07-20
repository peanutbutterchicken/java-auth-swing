A Java Swing Authentication Module for my LMS Project
    This is a personal project-based learning and may include bad practices, although i tried applying various concepts, you may find it still lacking in some areas.
    
    I will love for users to inquire questions and fixes on the code for this repository and i will answer them as much as i possibly can. 

As i previously said, this is used for my own personal project, but with a few configurations on 'Email credentials' and providing your own local 'mySQL file' this can work on your own machine.


Functionalities;
1. Provide clean and secure way of gmail authentication through OTP sent via gmail
2. Password security using mindrot's jBCrypt password hashing API, Incase the database is breached
2. Modern look and feel using Java Swing


MODIFICATION REQUIRED BEFORE RUNNING THIS PROGRAM:
JavaMail API and Google SMTP
1. Create your own config folder under the 'src' folder
2. Under this folder create a file named "SenderEmailCredentials.properties"
3. Inside the file have this on lines 1 and 2;
    senderEmail: YOUR@gmail.com
    appPassword: YOURAPPPASSWORD
4. This credentials can be obtained on your own Google account

Database
5. PROVIDE YOUR OWN SQL FILE, YOU CAN CONFIGURE IT ON 'EmailService.java'
6. UNDER 'DAO' FOLDER AND 'ConnectionProvider.java' FILE ARE FILES NECESSARY TO MODIFY TO PROVIDE CONNECTION WITH YOUR OWN SQL DATABASE

This steps may look like overwhelming for learners like me, but following the steps can provide you an overview how this works.
For learners like me, you can check this various topics for better understanding:
1. JavaMail API
2. Password hashing, salting, etc.
3. OOP, MVC, SOC


ENVIRONMENTl
Netbeans or;
Visual Studio Code with
    Language Support for Java(TM) by Red Hat

RUNNING THIS PROGRAM:
RUN 'Main.java'