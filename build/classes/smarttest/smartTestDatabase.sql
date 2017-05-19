
DROP DATABASE IF EXISTS smarttest_db;
CREATE DATABASE smarttest_db;
USE smarttest_db;

/*
* deployed tests table
*/
CREATE TABLE deployedTests(
    id INT AUTO_INCREMENT ,
    pincode VARCHAR(81) PRIMARY KEY,
    byteStr MEDIUMTEXT CHARACTER SET ascii
);
/*
* EXPERIMENTAL undeployed tests table - Gibriel
*/
CREATE TABLE undeployedTests(
    id INT AUTO_INCREMENT ,
    teacher TEXT,
    pincode VARCHAR(81) PRIMARY KEY,
    byteStr MEDIUMTEXT CHARACTER SET ascii
);

/*
* users table
*/
CREATE TABLE users(
    id INT AUTO_INCREMENT,
    uname VARCHAR(81) ,
    password VARCHAR(81) ,
    byteStr MEDIUMTEXT CHARACTER SET ascii,
    PRIMARY KEY (uname)
);

/*
* admins table
*/

CREATE TABLE admins(
    id INT AUTO_INCREMENT,
    uname VARCHAR(81),
    password VARCHAR(81),
    PRIMARY KEY (uname)
);

INSERT INTO admins(uname,password) VALUES ("arslan","notagoodyear");

/*
* learningoutcomes table
*/
CREATE TABLE learningOutcomes(
    id INT AUTO_INCREMENT PRIMARY KEY,
    byteStr MEDIUMTEXT CHARACTER SET ascii
);

INSERT INTO learningOutcomes(byteStr) VALUES ("INVALIDSTRINGDATA");