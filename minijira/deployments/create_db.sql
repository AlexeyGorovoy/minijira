DROP DATABASE minijira_db;
CREATE DATABASE minijira_db;
USE minijira_db;

CREATE TABLE customers (
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(50) NOT NULL,
	description VARCHAR(100) NOT NULL,
	PRIMARY KEY ( id )
);

CREATE TABLE people (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	surname VARCHAR(50) NOT NULL,
	mail VARCHAR(50) NOT NULL,
	skype VARCHAR(50) NOT NULL,
	site VARCHAR(50),
  password varchar(50) NOT NULL,
	PRIMARY KEY ( id )
);

CREATE TABLE projects (
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	description VARCHAR(100) NOT NULL,
	leader_id INT NOT NULL,
	customer_id INT NOT NULL,
	PRIMARY KEY ( id ), 
	FOREIGN KEY (leader_id)
		REFERENCES people (id),
	FOREIGN KEY (customer_id)
		REFERENCES customers(id)
);

CREATE TABLE skills (
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(50) NOT NULL,
	description VARCHAR(50) NOT NULL,
	PRIMARY KEY ( id )
);

CREATE TABLE used_skills (
  project_id INT NOT NULL,
  skill_id INT NOT NULL,
  PRIMARY KEY ( project_id, skill_id  )
);

CREATE TABLE people_skills (
	person_id INT NOT NULL,
	skill_id INT NOT NULL,
	PRIMARY KEY ( person_id, skill_id )
);