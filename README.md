A Java Swing Authentication Module for my LMS Project
    This is a personal project-based learning and may include bad practices, although i tried applying various concepts, you may find it still lacking in some areas.
    In terms of functionality the program works well.
    
    It would be greatly appreciated for users to inquire questions and fixes on the code for this repository and i will answer them as much as i possibly can. 

As i previously said, this is used for my own personal project, with a few configurations on 'Email credentials' and modifiying the sql port, root and password on 'EmailService.java'. Then, this can work on your own machine.



Functionalities;
1. Provide clean and secure way of gmail authentication through OTP sent via gmail.
2. Password security using mindrot's jBCrypt password hashing API, Incase the database is breached.
2. Modern look and feel using Java Swing.

Contains:
1. Java Swing Authentication Module full source code
2. SQL dump
3. Config file
4. Comments (or instructions) that explains how to modify parts of the code



MODIFICATION REQUIRED BEFORE RUNNING THIS PROGRAM:
JavaMail API and Google SMTP
1. Modify the config file 'senderEmailCredentials.properties' under the 'src' folder
2. Inside the file have this on lines 1 and 2;
    senderEmail: YOUR@gmail.com
    appPassword: YOURAPPPASSWORD
3. This credentials can be obtained on your own Google account

Database
5. IMPORT THE SQL FILE PROVIDED in 'src' folder.
6. UNDER 'DAO' FOLDER AND 'ConnectionProvider.java' FILE ARE FILES NECESSARY TO MODIFY TO PROVIDE CONNECTION WITH YOUR OWN SQL DATABASE (root, password, or even local port).




Various concepts and API I've used:
1. JavaMail API
2. Password hashing, salting, etc.
3. OOP, MVC, SOC

ENVIRONMENT:
Netbeans or;
Visual Studio Code with
    Language Support for Java(TM) by Red Hat

RUNNING THIS PROGRAM:
RUN 'Main.java'