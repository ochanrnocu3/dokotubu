CREATE TABLE ACCOUNTS (
 USER_ID VARCHAR (10) PRIMARY KEY,
 PASS VARCHAR (10) NOT NULL,
 MAIL VARCHAR (100) NOT NULL,
 NAME VARCHAR (40) NOT NULL,
 AGE INT NOT NULL
);

CREATE TABLE MUTTERS (
 ID INT PRIMARY KEY AUTO_INCREMENT,
 USER_ID VARCHAR (10) NOT NULL,
 NAME VARCHAR (100) NOT NULL,
 TEXT VARCHAR (255) NOT NULL
);

CREATE TABLE COMMENTS (
 ID INT PRIMARY KEY AUTO_INCREMENT,
 MUTTERS_ID INT NOT NULL,
 USER_ID VARCHAR (10) NOT NULL,
 COMMENT VARCHAR (255) NOT NULL
);