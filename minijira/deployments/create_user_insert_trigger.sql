USE `minijira`;
DELIMITER $$

CREATE TRIGGER user_insert 
	BEFORE INSERT ON `user` 
	FOR EACH ROW SET NEW.password = PASSWORD(NEW.password);
