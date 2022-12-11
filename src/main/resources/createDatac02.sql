create database tec02database;
use tec02database;

CREATE TABLE role (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    role_code INT NOT NULL,
    rolename VARCHAR(50) NOT NULL,
    creationby INT,
    creationtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
insert into role(role_code, rolename) value(1, "admin");

CREATE TABLE user (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    userpass VARCHAR(100) NOT NULL,
    role_id INT NOT NULL,
    user_status BOOLEAN NOT NULL,
    creationby INT,
    creationtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id)
        REFERENCES role (id)
);

insert into user(username, userpass, role_id, user_status) value("admin", "admin", 1, true);

CREATE TABLE location (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    product VARCHAR(20) NOT NULL,
    station VARCHAR(20) NOT NULL,
    line VARCHAR(20) NOT NULL,
    creationby INT NOT NULL,
    creationtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (creationby)
        REFERENCES user (id)
);

CREATE TABLE pc (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    pcname VARCHAR(20) NOT NULL,
    location_id INT NOT NULL,
    creationby INT NOT NULL,
    creationtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (creationby)
        REFERENCES user (id),
    FOREIGN KEY (location_id)
        REFERENCES location (id)
);

CREATE TABLE pcinfo (
    id INT NOT NULL PRIMARY KEY,
    pcinfoos VARCHAR(35),
    pcinfoip VARCHAR(35),
    pcinfomac VARCHAR(35),
    pcinfoAll json,
    updateTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id)
        REFERENCES pc (id)
);

CREATE TABLE program (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    program_name VARCHAR(20),
    creationby INT NOT NULL,
    creationtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (creationby)
        REFERENCES user (id)
);

CREATE TABLE programversion (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    program_id INT NOT NULL,
    programversionname VARCHAR(15) NOT NULL,
    programversionpath TEXT NOT NULL,
	approveby INT NOT NULL,
    creationby INT NOT NULL,
    creationtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (program_id)
        REFERENCES program (id),
    FOREIGN KEY (creationby)
        REFERENCES user (id)
);

CREATE TABLE groupconfig (
    id INT KEY NOT NULL PRIMARY KEY AUTO_INCREMENT,
    groupconfigname VARCHAR(20) NOT NULL,
    creationby INT NOT NULL,
    creationtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (creationby)
        REFERENCES user (id)
);

CREATE TABLE config (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    groupconfig_id INT NOT NULL,
    config_name VARCHAR(20),
    creationby INT NOT NULL,
    creationtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (groupconfig_id)
        REFERENCES groupconfig (id),
    FOREIGN KEY (creationby)
        REFERENCES user (id)
);

CREATE TABLE configversion (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    config_id INT NOT NULL,
    configversionname VARCHAR(15),
    configversionpath TEXT,
    approveby INT NOT NULL,
    creationby INT NOT NULL,
    creationtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (config_id)
        REFERENCES config (id),
    FOREIGN KEY (creationby)
        REFERENCES user (id)
);

CREATE TABLE pcProgram (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    pc_id INT NOT NULL,
    program_id INT NOT NULL,
    config_id INT NOT NULL,
    creationby INT NOT NULL,
    creationtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pc_id)
        REFERENCES pc (id),
    FOREIGN KEY (program_id)
        REFERENCES program (id),
    FOREIGN KEY (config_id)
        REFERENCES config (id),
    FOREIGN KEY (creationby)
        REFERENCES user (id)
);

#drop database tec02database;

