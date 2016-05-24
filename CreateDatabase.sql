CREATE DATABASE automobile_db;
USE automobile_db;
CREATE TABLE automobile(id INT AUTO_INCREMENT, make varchar(255) NOT NULL, model varchar(255) NOT NULL, baseprice FLOAT NOT NULL, PRIMARY KEY (id));
CREATE TABLE optionset(id INT AUTO_INCREMENT, name varchar(255) NOT NULL, autoid INT NOT NULL, PRIMARY KEY (id), FOREIGN KEY (autoid) REFERENCES automobile(id) ON DELETE CASCADE);
CREATE TABLE options(id INT AUTO_INCREMENT, name varchar(255) NOT NULL, price FLOAT NOT NULL, optionsetid INT NOT NULL, PRIMARY KEY (id), FOREIGN KEY (optionsetid) REFERENCES optionset(id) ON DELETE CASCADE);
