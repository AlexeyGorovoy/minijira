SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `minijira` ;
CREATE SCHEMA IF NOT EXISTS `minijira` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `minijira` ;

-- -----------------------------------------------------
-- Table `minijira`.`office`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`office` (
  `office_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`office_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`employee` (
  `employee_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(50) NOT NULL,
  `date_hired` DATE NOT NULL,
  `phonenumber` VARCHAR(15) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `skype` VARCHAR(45) NULL,
  `office_id` INT NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`employee_id`),
  INDEX `fk_employee_office1_idx` (`office_id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  CONSTRAINT `fk_employee_office1`
    FOREIGN KEY (`office_id`)
    REFERENCES `minijira`.`office` (`office_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`priority`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`priority` (
  `priority_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`priority_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`workflow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`workflow` (
  `workflow_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`workflow_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`project_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`project_type` (
  `project_type_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`project_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(150) NULL,
  `address` VARCHAR(100) NULL,
  `info` VARCHAR(150) NULL,
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`manager_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`manager_type` (
  `manager_type_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(150) NULL,
  PRIMARY KEY (`manager_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`manager` (
  `employee_id` INT NOT NULL,
  `manager_type_id` INT NOT NULL,
  PRIMARY KEY (`employee_id`),
  INDEX `fk_manager_manager_type1_idx` (`manager_type_id` ASC),
  CONSTRAINT `fk_manager_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `minijira`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_manager_manager_type1`
    FOREIGN KEY (`manager_type_id`)
    REFERENCES `minijira`.`manager_type` (`manager_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`rank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`rank` (
  `rank_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`rank_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`dev_tech`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`dev_tech` (
  `dev_tech_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(150) NULL,
  PRIMARY KEY (`dev_tech_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`developer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`developer` (
  `employee_id` INT NOT NULL,
  `rank_id` INT NOT NULL,
  `main_tech_id` INT NOT NULL,
  INDEX `fk_developer_employee1_idx` (`employee_id` ASC),
  PRIMARY KEY (`employee_id`),
  INDEX `fk_developer_dev_rank1_idx` (`rank_id` ASC),
  INDEX `fk_developer_dev_tech1_idx` (`main_tech_id` ASC),
  CONSTRAINT `fk_developer_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `minijira`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_developer_dev_rank1`
    FOREIGN KEY (`rank_id`)
    REFERENCES `minijira`.`rank` (`rank_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_developer_dev_tech1`
    FOREIGN KEY (`main_tech_id`)
    REFERENCES `minijira`.`dev_tech` (`dev_tech_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`test_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`test_type` (
  `test_type_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(150) NULL,
  PRIMARY KEY (`test_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`tester`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`tester` (
  `employee_id` INT NOT NULL,
  `test_type_id` INT NOT NULL,
  `rank_id` INT NOT NULL,
  PRIMARY KEY (`employee_id`),
  INDEX `fk_tester_test_type1_idx` (`test_type_id` ASC),
  INDEX `fk_tester_test_rank1_idx` (`rank_id` ASC),
  CONSTRAINT `fk_tester_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `minijira`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tester_test_rank1`
    FOREIGN KEY (`rank_id`)
    REFERENCES `minijira`.`rank` (`rank_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tester_test_type1`
    FOREIGN KEY (`test_type_id`)
    REFERENCES `minijira`.`test_type` (`test_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`customer_agent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`customer_agent` (
  `employee_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`employee_id`),
  INDEX `fk_customer_agent_customer1_idx` (`customer_id` ASC),
  CONSTRAINT `fk_customer_agent_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `minijira`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_agent_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `minijira`.`customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`project` (
  `project_id` INT NOT NULL AUTO_INCREMENT,
  `project_type_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(150) NOT NULL,
  `date_start` DATE NULL,
  `date_end` DATE NULL,
  `dev_lead_id` INT NOT NULL,
  `test_lead_id` INT NOT NULL,
  `pm_id` INT NOT NULL,
  `customer_agent_id` INT NULL,
  PRIMARY KEY (`project_id`),
  INDEX `fk_project_project_type1_idx` (`project_type_id` ASC),
  INDEX `fk_project_customer1_idx` (`customer_id` ASC),
  INDEX `fk_project_developer1_idx` (`dev_lead_id` ASC),
  INDEX `fk_project_tester1_idx` (`test_lead_id` ASC),
  INDEX `fk_project_customer_agent1_idx` (`customer_agent_id` ASC),
  INDEX `fk_project_manager1_idx` (`pm_id` ASC),
  CONSTRAINT `fk_project_project_type1`
    FOREIGN KEY (`project_type_id`)
    REFERENCES `minijira`.`project_type` (`project_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `minijira`.`customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_manager1`
    FOREIGN KEY (`pm_id`)
    REFERENCES `minijira`.`manager` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_developer1`
    FOREIGN KEY (`dev_lead_id`)
    REFERENCES `minijira`.`developer` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_tester1`
    FOREIGN KEY (`test_lead_id`)
    REFERENCES `minijira`.`tester` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_customer_agent1`
    FOREIGN KEY (`customer_agent_id`)
    REFERENCES `minijira`.`customer_agent` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`task` (
  `task_id` INT NOT NULL AUTO_INCREMENT,
  `priority_id` INT NOT NULL,
  `workflow_id` INT NOT NULL,
  `assignee_id` INT NOT NULL,
  `reporter_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(150) NULL,
  `dueto` DATETIME NULL,
  `closed` TINYINT(1) NOT NULL,
  PRIMARY KEY (`task_id`),
  INDEX `fk_task_employee1_idx` (`assignee_id` ASC),
  INDEX `fk_task_employee2_idx` (`reporter_id` ASC),
  INDEX `fk_task_priority1_idx` (`priority_id` ASC),
  INDEX `fk_task_workflow1_idx` (`workflow_id` ASC),
  INDEX `fk_task_project1_idx` (`project_id` ASC),
  CONSTRAINT `fk_task_employee1`
    FOREIGN KEY (`assignee_id`)
    REFERENCES `minijira`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_employee2`
    FOREIGN KEY (`reporter_id`)
    REFERENCES `minijira`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_priority1`
    FOREIGN KEY (`priority_id`)
    REFERENCES `minijira`.`priority` (`priority_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_workflow1`
    FOREIGN KEY (`workflow_id`)
    REFERENCES `minijira`.`workflow` (`workflow_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `minijira`.`project` (`project_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`project_tech_joint`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`project_tech_joint` (
  `project_id` INT NOT NULL,
  `dev_tech_id` INT NOT NULL,
  INDEX `fk_project_tech_project1_idx` (`project_id` ASC),
  INDEX `fk_project_tech_dev_tech1_idx` (`dev_tech_id` ASC),
  PRIMARY KEY (`project_id`, `dev_tech_id`),
  CONSTRAINT `fk_project_tech_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `minijira`.`project` (`project_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_tech_dev_tech1`
    FOREIGN KEY (`dev_tech_id`)
    REFERENCES `minijira`.`dev_tech` (`dev_tech_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`dev_tech_joint`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`dev_tech_joint` (
  `developer_id` INT NOT NULL,
  `dev_tech_id` INT NOT NULL,
  `experience` INT NOT NULL,
  PRIMARY KEY (`developer_id`, `dev_tech_id`),
  INDEX `fk_dev_tech_joint_developer1_idx` (`developer_id` ASC),
  CONSTRAINT `fk_dev_tech_joint_developer1`
    FOREIGN KEY (`developer_id`)
    REFERENCES `minijira`.`developer` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dev_tech_joint_dev_tech1`
    FOREIGN KEY (`dev_tech_id`)
    REFERENCES `minijira`.`dev_tech` (`dev_tech_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  `text` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `fk_comment_employee_idx` (`employee_id` ASC),
  INDEX `fk_comment_project_idx` (`project_id` ASC),
  CONSTRAINT `fk_comment_employee`
    FOREIGN KEY (`employee_id`)
    REFERENCES `minijira`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_project`
    FOREIGN KEY (`project_id`)
    REFERENCES `minijira`.`project` (`project_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`project_employee_joint`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`project_employee_joint` (
  `employee_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  `active` TINYINT(1) NOT NULL,
  PRIMARY KEY (`employee_id`, `project_id`),
  INDEX `fk_pej_project1_idx` (`project_id` ASC),
  CONSTRAINT `fk_pej_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `minijira`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pej_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `minijira`.`project` (`project_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`user` (
  `employee_id` INT NOT NULL,
  `username` VARCHAR(16) NOT NULL,
  `password` VARCHAR(41) NOT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  CONSTRAINT `fk_user_employee`
    FOREIGN KEY (`employee_id`)
    REFERENCES `minijira`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `minijira`.`office`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`office` (`office_id`, `title`, `address`) VALUES (1, 'HeadQuarters', 'P.O. Box 162, 2746 Id Avenue');
INSERT INTO `minijira`.`office` (`office_id`, `title`, `address`) VALUES (2, 'Support', '478-7838 Eget, Avenue');
INSERT INTO `minijira`.`office` (`office_id`, `title`, `address`) VALUES (3, 'Externals', 'All over the world.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`employee`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (1, 'Nicholas', 'Vega', '20.03.2013', '(64) 171-5210', 'malesuada.vel@lobortis.com', 'et', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (2, 'Fallon', 'Lane', '02.04.2010', '(17) 443-5814', 'et.netus@nisl.ca', 'est', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (3, 'Hiroko', 'Pennington', '15.03.2013', '(16) 910-5228', 'et.risus.Quisque@tincidunt.co.uk', 'lectus', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (4, 'Fleur', 'Craft', '18.02.2010', '(21) 457-4720', 'felis@purussapien.co.uk', 'Quisque', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (5, 'Xenos', 'Briggs', '02.07.2010', '(52) 420-3341', 'amet@duilectus.org', 'tempus', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (6, 'Alfonso', 'Harris', '02.12.2008', '(37) 720-5703', 'mi.Aliquam.gravida@mattisvelit.org', 'egestas', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (7, 'Nash', 'Moreno', '01.02.2011', '(60) 273-4188', 'Nunc.quis.arcu@nonummyFuscefermentum.co.uk', 'arcu.', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (8, 'Devin', 'Mcintosh', '13.12.2011', '(49) 678-6070', 'facilisis.vitae@molestiepharetranibh.ca', 'Cras', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (9, 'Fredericka', 'Compton', '01.07.2012', '(85) 163-7457', 'sodales@non.co.uk', 'imperdiet', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (10, 'Malik', 'Shepherd', '05.04.2011', '(87) 693-6662', 'Quisque.nonummy@laciniaat.co.uk', 'amet', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (11, 'Kaden', 'Jones', '11.11.2009', '(59) 129-5254', 'lorem@orci.net', 'lorem', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (12, 'Lucian', 'Carpenter', '10.01.2012', '(42) 866-9238', 'rutrum.magna@Sed.ca', 'nostra,', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (13, 'Oren', 'Delacruz', '08.05.2013', '(49) 882-6833', 'consectetuer.adipiscing@dolorNullasemper.org', 'auctor.', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (14, 'Wynne', 'Suarez', '20.12.2011', '(51) 225-3537', 'ipsum.primis.in@arcuVivamus.ca', 'diam', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (15, 'Ivy', 'Myers', '30.03.2011', '(52) 999-9863', 'velit@Vivamus.net', 'dui.', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (16, 'Aiko', 'Wright', '12.10.2009', '(46) 414-6552', 'lobortis@natoquepenatibus.edu', 'pede.', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (17, 'Victoria', 'Soto', '15.10.2010', '(68) 598-8285', 'ullamcorper.eu.euismod@rhoncusDonec.org', 'id,', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (18, 'Jada', 'Mercado', '15.03.2012', '(60) 280-7477', 'Nullam.feugiat@inceptoshymenaeos.edu', 'adipiscing', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (19, 'Wyatt', 'Franco', '13.07.2013', '(61) 209-1664', 'Cras.dolor.dolor@eratvolutpatNulla.com', 'euismod', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (20, 'Alvin', 'Waters', '16.12.2009', '(34) 799-1510', 'mauris@at.ca', 'Fusce', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (21, 'Marsden', 'Campos', '05.09.2008', '(52) 790-4746', 'odio.auctor.vitae@tellusPhasellus.com', 'Suspendisse', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (22, 'Casey', 'Bartlett', '26.08.2011', '(47) 248-2417', 'non.justo.Proin@netuset.edu', 'natoque', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (23, 'Aretha', 'Nielsen', '16.03.2010', '(98) 155-1212', 'odio.a@Crasvehicula.net', 'sit', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (24, 'Dillon', 'Zimmerman', '12.01.2010', '(87) 254-7938', 'commodo.at@ametdiameu.co.uk', 'elit', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (25, 'Inez', 'Gentry', '06.07.2012', '(46) 560-9047', 'Fusce@posuere.co.uk', 'eget,', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (26, 'Imelda', 'Deleon', '23.10.2008', '(23) 509-4012', 'tempus.scelerisque.lorem@enimMaurisquis.net', 'scelerisque', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (27, 'Ivana', 'Herring', '02.09.2008', '(64) 837-9398', 'neque@malesuada.ca', 'lectus.', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (28, 'Lionel', 'Lara', '31.05.2012', '(46) 764-2359', 'Fusce.mollis.Duis@Maurisvel.net', 'velit', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (29, 'Cassady', 'Norman', '10.12.2009', '(95) 687-1016', 'eu.accumsan@purus.org', 'eget', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (30, 'Benedict', 'Elliott', '06.02.2013', '(94) 892-1449', 'dictum.ultricies@ullamcorper.ca', 'lacinia', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (31, 'Fleur', 'Graham', '10.06.2011', '(73) 530-5519', 'Curabitur.sed@non.edu', 'sed', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (32, 'Yolanda', 'Sellers', '13.03.2009', '(63) 357-8573', 'bibendum@anteNuncmauris.edu', 'Pellentesque', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (33, 'Judah', 'Bernard', '02.04.2012', '(60) 354-3502', 'blandit@Morbi.edu', 'Donec', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (34, 'Mary', 'Gibson', '24.11.2012', '(22) 154-5106', 'Nunc@Cum.net', 'euismod', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (35, 'Cyrus', 'Mitchell', '09.09.2008', '(88) 709-3293', 'diam.eu.dolor@ac.org', 'tempus', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (36, 'Stella', 'Prince', '15.02.2009', '(70) 843-9665', 'tempus.eu.ligula@velesttempor.edu', 'libero.', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (37, 'Alisa', 'Mclaughlin', '03.03.2009', '(42) 671-8731', 'tellus@at.org', 'mollis', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (38, 'Russell', 'Holder', '18.08.2011', '(54) 102-3862', 'erat.nonummy@Cras.com', 'bibendum', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (39, 'Victor', 'Ratliff', '25.09.2010', '(57) 168-7660', 'suscipit.nonummy@adipiscinglobortisrisus.net', 'condimentum', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (40, 'Ray', 'Lucas', '09.09.2011', '(66) 381-1222', 'nonummy.ac.feugiat@Nam.org', 'volutpat.', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (41, 'Alma', 'Ochoa', '28.07.2010', '(47) 344-5362', 'sed.hendrerit@ante.ca', 'tortor.', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (42, 'Iliana', 'Smith', '01.08.2012', '(46) 264-1704', 'lobortis.ultrices@Cumsociis.edu', 'posuere', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (43, 'Jelani', 'Parks', '25.04.2010', '(27) 434-4458', 'nec@dolor.org', 'nonummy', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (44, 'Aquila', 'Head', '04.02.2012', '(28) 464-2181', 'sed.dictum@vulputateullamcorper.ca', 'arcu.', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (45, 'Wing', 'Snyder', '01.03.2012', '(83) 574-5048', 'lobortis@variustor.ca', 'placerat', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (46, 'Emerald', 'Duran', '15.08.2009', '(94) 118-5700', 'dolor@Integer.org', 'velit.', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (47, 'Ariana', 'Sloan', '13.06.2012', '(90) 380-7843', 'nisl.arcu@egestasAliquamfringilla.com', 'sem,', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (48, 'Harriet', 'Barr', '23.08.2013', '(41) 143-5548', 'consectetuer@convallis.co.uk', 'augue', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (49, 'Sloane', 'Caldwell', '11.05.2009', '(76) 612-9811', 'Nunc.sed.orci@ipsumdolor.org', 'velit', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (50, 'William', 'Bowman', '12.01.2012', '(64) 644-6415', 'semper.auctor@diamat.co.uk', 'nonummy', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (51, 'Bell', 'Guerra', '29.10.2012', '(29) 528-5780', 'Quisque.ornare@etrutrumnon.ca', 'luctus', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (52, 'Zephania', 'Simon', '22.09.2008', '(74) 129-2651', 'Sed.congue.elit@magna.co.uk', 'dictum', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (53, 'Brian', 'Barry', '30.01.2011', '(29) 914-8193', 'consequat.dolor@Mauris.net', 'Proin', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (54, 'Ann', 'Noble', '22.12.2011', '(67) 284-9158', 'ac@pharetraQuisqueac.org', 'fames', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (55, 'Katelyn', 'Dunlap', '08.11.2010', '(16) 342-4761', 'Sed.eu@eu.net', 'ac,', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (56, 'Magee', 'Hendrix', '25.10.2011', '(37) 910-2035', 'aliquet.metus.urna@eu.net', 'quam', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (57, 'Blaine', 'Weiss', '31.07.2010', '(88) 693-7155', 'sem@Proin.com', 'commodo', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (58, 'Giselle', 'Velazquez', '11.08.2012', '(74) 323-9356', 'mi@euismodindolor.net', 'Vestibulum', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (59, 'Miranda', 'Vaughan', '30.10.2011', '(48) 580-4055', 'necenim@purusisque.net', 'viverra', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (60, 'Audrey', 'Palmer', '27.08.2011', '(13) 264-2338', 'adipiscing.lacus@loremeget.org', 'eget', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (61, 'Darius', 'Higgins', '20.08.2009', '(72) 922-1209', 'non.dui@inceptris.com', 'nisi.', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (62, 'Xavier', 'Rodriquez', '19.01.2011', '(63) 243-2025', 'proin.sed@masuadel.com', 'tincidunt', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (63, 'Kameko', 'Fischer', '01.02.2013', '(97) 482-6858', 'semper@leoMorbineque.net', 'sit', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (64, 'Carl', 'Lawson', '12.11.2011', '(41) 796-3745', 'semper.et.lacinia@iaculis.com', 'id', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (65, 'Brady', 'Battle', '04.04.2012', '(19) 681-6713', 'hymenaeos.Mauris.ut@nonbibendum.ca', 'Donec', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (66, 'Kessie', 'Floyd', '03.08.2011', '(52) 890-2014', 'ligula.elit.pretium@pedesagittis.ca', 'est,', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (67, 'Jameson', 'Mccullough', '08.11.2011', '(11) 730-9845', 'ridiculus.mus@felis.edu', 'nisl.', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (68, 'Barclay', 'Horne', '06.05.2010', '(71) 619-2777', 'felis@erat.com', 'dapibus', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (69, 'Michael', 'Hood', '03.02.2013', '(45) 990-6186', 'amet.risus@Integersemelit.com', 'adipiscing,', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (70, 'Irene', 'Rollins', '12.08.2009', '(71) 543-7600', 'amet@nibhsitamet.co.uk', 'eu', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (71, 'Timothy', 'Ewing', '10.06.2011', '(90) 265-7459', 'libero@ipsumnon.net', 'dis', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (72, 'Colby', 'Schwartz', '13.10.2012', '(48) 839-4033', 'a@ridiculus.net', 'risus', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (73, 'Indigo', 'Salas', '19.03.2011', '(48) 331-4621', 'mollis@massarutrum.com', 'dui', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (74, 'Lionel', 'Camacho', '21.11.2010', '(36) 568-3841', 'non@luctusvulputate.org', 'malesuada', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (75, 'Piper', 'Lyons', '20.11.2012', '(64) 876-7069', 'eros@Integerin.co.uk', 'mauris', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (76, 'Joseph', 'Berg', '12.12.2009', '(33) 534-6930', 'nec@cursuspurusNullam.edu', 'non', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (77, 'Sonia', 'Skinner', '23.09.2011', '(49) 522-1869', 'magna@Integertincidunt.co.uk', 'amet,', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (78, 'Cynthia', 'Cruz', '12.10.2008', '(21) 514-1932', 'Sed@ipsum.com', 'semper', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (79, 'Lysandra', 'Donovan', '03.12.2008', '(64) 854-5952', 'amet.risus.Donec@sitamet.com', 'lacus', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (80, 'Hadassah', 'Buck', '06.07.2012', '(63) 484-4947', 'a@conubia.ca', 'nec', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (81, 'Basil', 'Decker', '02.09.2010', '(60) 788-8480', 'erat@acipsum.edu', 'lorem', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (82, 'Tad', 'Daniel', '10.09.2012', '(98) 768-6011', 'at.auctor.ullamcorper@Lorem.co.uk', 'iaculis', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (83, 'Quinlan', 'Curtis', '04.08.2010', '(61) 172-4244', 'tristique.senectus.et@loremsemper.edu', 'ut,', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (84, 'Palmer', 'Bradford', '25.10.2008', '(57) 365-5954', 'molestie.pharetra@malesuada.co.uk', 'vitae,', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (85, 'Mona', 'Monroe', '08.08.2011', '(18) 158-2802', 'interdum@cursusluctusipsum.org', 'Aliquam', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (86, 'Callie', 'Copeland', '30.05.2009', '(91) 734-1509', 'et.magnis.dis@placeratCras.net', 'blandit', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (87, 'Cooper', 'Haney', '28.02.2011', '(80) 591-4928', 'lorem.sit@Sedpharetrafelis.ca', 'Proin', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (88, 'Walter', 'Mccarthy', '13.07.2012', '(54) 643-3199', 'rutrum.justo@dolor.com', 'Sed', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (89, 'Ezekiel', 'Hancock', '13.06.2011', '(82) 668-3669', 'orci.luctus.et@rutrumloremac.net', 'metus.', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (90, 'Delilah', 'Pratt', '18.01.2010', '(23) 619-2395', 'risus.varius@consectetueradipiscingelit.com', 'ultrices', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (91, 'Wallace', 'Watkins', '20.11.2011', '(96) 683-8776', 'auctor@malesuadavelconvallis.co.uk', 'luctus', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (92, 'Astra', 'Dickson', '12.07.2011', '(74) 320-6009', 'vel@erosNamconsequat.ca', 'sagittis', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (93, 'Kieran', 'Snyder', '07.04.2010', '(63) 624-3131', 'tellus.lorem@blandit.ca', 'sociis', 3, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (94, 'Rinah', 'Pearson', '08.05.2013', '(58) 740-4310', 'dolor.nonummy@Donecdignissim.ca', 'felis,', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (95, 'September', 'Hammond', '08.06.2011', '(50) 774-5900', 'vitae.aliquet@felirra.ca', 'magna.', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (96, 'Zelenia', 'Sims', '29.06.2010', '(53) 151-9185', 'eros@sitamet.ca', 'Nulla', 1, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (97, 'Haviva', 'Scott', '16.02.2011', '(73) 970-9634', 'nisi.magna@aauctor.co.uk', 'erat', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (98, 'Lacy', 'Thornton', '10.05.2010', '(37) 788-8235', 'orci.quis.lectus@ametronec.com', 'egestas.', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (99, 'Blythe', 'Ferrell', '10.04.2011', '(37) 672-2150', 'erctetuer@dissesagittis.com', 'vestibulum,', 2, 'pass');
INSERT INTO `minijira`.`employee` (`employee_id`, `name`, `surname`, `date_hired`, `phonenumber`, `email`, `skype`, `office_id`, `password`) VALUES (100, 'Brenden', 'Fulton', '25.01.2009', '(44) 790-3856', 'quisque@feugiratvelit.com', 'arcu.', 1, 'pass');

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`priority`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`priority` (`priority_id`, `title`) VALUES (NULL, 'Low');
INSERT INTO `minijira`.`priority` (`priority_id`, `title`) VALUES (NULL, 'Normal');
INSERT INTO `minijira`.`priority` (`priority_id`, `title`) VALUES (NULL, 'High');
INSERT INTO `minijira`.`priority` (`priority_id`, `title`) VALUES (NULL, 'Extreme');

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`workflow`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`workflow` (`workflow_id`, `title`) VALUES (NULL, 'In progress');
INSERT INTO `minijira`.`workflow` (`workflow_id`, `title`) VALUES (NULL, 'Done');
INSERT INTO `minijira`.`workflow` (`workflow_id`, `title`) VALUES (NULL, 'Paused');
INSERT INTO `minijira`.`workflow` (`workflow_id`, `title`) VALUES (NULL, 'Suspended');

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`project_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`project_type` (`project_type_id`, `title`, `description`) VALUES (NULL, 'Web', 'Site or service');
INSERT INTO `minijira`.`project_type` (`project_type_id`, `title`, `description`) VALUES (NULL, 'WebAPI', 'Interface for some service');
INSERT INTO `minijira`.`project_type` (`project_type_id`, `title`, `description`) VALUES (NULL, 'Native', 'Native C++ application for some platform');
INSERT INTO `minijira`.`project_type` (`project_type_id`, `title`, `description`) VALUES (NULL, 'Enterprise', 'Full or part of enterprise application');
INSERT INTO `minijira`.`project_type` (`project_type_id`, `title`, `description`) VALUES (NULL, 'Entertaiment', 'Social networking or gaming software');

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`customer` (`customer_id`, `title`, `description`, `address`, `info`) VALUES (1, 'Nunc Sed Libero Inc.', 'ligula. Donec luctus aliquet odio. Etiam ligula tortor,', 'P.O. Box 584, 5145 Consectetuer Road', '');
INSERT INTO `minijira`.`customer` (`customer_id`, `title`, `description`, `address`, `info`) VALUES (2, 'Sed Consulting', 'Donec sollicitudin adipiscing ligula. Aenean gravida nunc sed', 'Ap #628-9957 Ut St.', '');
INSERT INTO `minijira`.`customer` (`customer_id`, `title`, `description`, `address`, `info`) VALUES (3, 'Gravida Non Sollicitudin PC', 'Donec vitae erat vel pede', '1323 Mollis. Rd.', '');
INSERT INTO `minijira`.`customer` (`customer_id`, `title`, `description`, `address`, `info`) VALUES (4, 'Eros Proin Ultrices Corp.', 'erat. Sed nunc est, mollis non, cursus non,', '689 Nunc Street', '');
INSERT INTO `minijira`.`customer` (`customer_id`, `title`, `description`, `address`, `info`) VALUES (5, 'Lacinia Vitae Limited', 'consequat purus. Maecenas libero est, congue a, aliquet', '665-2170 Cras St.', '');
INSERT INTO `minijira`.`customer` (`customer_id`, `title`, `description`, `address`, `info`) VALUES (6, 'Sed Inc.', 'ornare. Fusce mollis. Duis sit amet diam eu dolor', '9438 Cursus Avenue', '');
INSERT INTO `minijira`.`customer` (`customer_id`, `title`, `description`, `address`, `info`) VALUES (7, 'Suspendisse Commodo Corp.', 'dui. Suspendisse ac metus vitae velit egestas', '8510 Sem Av.', '');
INSERT INTO `minijira`.`customer` (`customer_id`, `title`, `description`, `address`, `info`) VALUES (8, 'Neque Industries', 'enim nisl elementum purus, accumsan interdum libero dui', 'P.O. Box 129, 2088 Orci. St.', '');
INSERT INTO `minijira`.`customer` (`customer_id`, `title`, `description`, `address`, `info`) VALUES (9, 'Ante Blandit LLP', 'volutpat. Nulla facilisis. Suspendisse commodo tincidunt nibh. Phasellus nulla.', 'P.O. Box 531, 3624 Cursus, Road', '');
INSERT INTO `minijira`.`customer` (`customer_id`, `title`, `description`, `address`, `info`) VALUES (10, 'Non Foundation', 'ornare sagittis felis. Donec tempor, est ac mattis semper, dui', 'Ap #915-5540 Tristique Ave', '');

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`manager_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`manager_type` (`manager_type_id`, `title`, `description`) VALUES (NULL, 'HR', 'Human resources manager.');
INSERT INTO `minijira`.`manager_type` (`manager_type_id`, `title`, `description`) VALUES (NULL, 'PM', 'Project manager.');
INSERT INTO `minijira`.`manager_type` (`manager_type_id`, `title`, `description`) VALUES (NULL, 'TOP', 'All kinds of management in top level.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`manager`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`manager` (`employee_id`, `manager_type_id`) VALUES (81, 2);
INSERT INTO `minijira`.`manager` (`employee_id`, `manager_type_id`) VALUES (82, 3);
INSERT INTO `minijira`.`manager` (`employee_id`, `manager_type_id`) VALUES (83, 1);
INSERT INTO `minijira`.`manager` (`employee_id`, `manager_type_id`) VALUES (84, 3);
INSERT INTO `minijira`.`manager` (`employee_id`, `manager_type_id`) VALUES (85, 3);
INSERT INTO `minijira`.`manager` (`employee_id`, `manager_type_id`) VALUES (86, 1);
INSERT INTO `minijira`.`manager` (`employee_id`, `manager_type_id`) VALUES (87, 1);
INSERT INTO `minijira`.`manager` (`employee_id`, `manager_type_id`) VALUES (88, 2);
INSERT INTO `minijira`.`manager` (`employee_id`, `manager_type_id`) VALUES (89, 1);
INSERT INTO `minijira`.`manager` (`employee_id`, `manager_type_id`) VALUES (90, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`rank`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`rank` (`rank_id`, `title`) VALUES (NULL, 'Student');
INSERT INTO `minijira`.`rank` (`rank_id`, `title`) VALUES (NULL, 'Junior');
INSERT INTO `minijira`.`rank` (`rank_id`, `title`) VALUES (NULL, 'Middle');
INSERT INTO `minijira`.`rank` (`rank_id`, `title`) VALUES (NULL, 'Senior');

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`dev_tech`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'C/C++', 'Native languages for most systems.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'Java', 'Object-oriented programming language for common development.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'C#', 'Object-oriented programming language for .NET development.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'ASP.NET', 'Web technology for .NET developers.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'JSF', 'Web view technology for Java.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'Facelets', 'HTML templates for Java.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'Twitter Bootstrap', 'Common HTML and CSS bootstrap.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'HTML + CSS', 'Boooring verstka.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'Shell', 'Unix common prompt.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'Haskell', 'Functional programming.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'Ruby on Rails', 'New mistery technology.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'PHP', 'Plain old web development.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'Zend Framework', 'PHP web standarts.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'Yii Framework', 'PHP web framework.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'Scala', 'Ruby for old Java developers.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'Play Framework', 'Java web framework.');
INSERT INTO `minijira`.`dev_tech` (`dev_tech_id`, `title`, `description`) VALUES (NULL, 'JPA', 'Java persistence API.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`developer`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (1, 1, 7);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (2, 4, 14);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (3, 3, 6);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (4, 3, 10);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (5, 3, 9);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (6, 1, 8);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (7, 2, 14);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (8, 4, 17);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (9, 3, 8);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (10, 1, 16);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (11, 4, 15);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (12, 1, 15);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (13, 2, 3);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (14, 1, 9);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (15, 2, 15);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (16, 4, 9);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (17, 1, 15);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (18, 2, 14);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (19, 1, 14);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (20, 2, 16);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (21, 2, 7);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (22, 2, 11);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (23, 1, 3);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (24, 3, 13);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (25, 3, 11);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (26, 3, 7);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (27, 2, 14);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (28, 1, 15);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (29, 4, 4);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (30, 2, 6);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (31, 1, 5);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (32, 4, 4);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (33, 1, 3);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (34, 4, 6);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (35, 4, 6);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (36, 1, 4);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (37, 3, 9);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (38, 4, 15);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (39, 3, 7);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (40, 3, 4);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (41, 1, 5);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (42, 3, 8);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (43, 1, 13);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (44, 2, 17);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (45, 4, 11);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (46, 2, 17);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (47, 4, 3);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (48, 1, 2);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (49, 2, 3);
INSERT INTO `minijira`.`developer` (`employee_id`, `rank_id`, `main_tech_id`) VALUES (50, 2, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`test_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`test_type` (`test_type_id`, `title`, `description`) VALUES (NULL, 'Functional', ' A type of black box testing that bases its test cases on the specifications of the software component under test. ');
INSERT INTO `minijira`.`test_type` (`test_type_id`, `title`, `description`) VALUES (NULL, 'Security', 'A process to determine that an information system protects data and maintains functionality as intended.');
INSERT INTO `minijira`.`test_type` (`test_type_id`, `title`, `description`) VALUES (NULL, 'Performance and Stress', 'General testing performed to determine how a system performs in terms of responsiveness and stability under a particular workload.');
INSERT INTO `minijira`.`test_type` (`test_type_id`, `title`, `description`) VALUES (NULL, 'Usability', 'A technique used to evaluate a product by testing it on users.');
INSERT INTO `minijira`.`test_type` (`test_type_id`, `title`, `description`) VALUES (NULL, 'Build Verification', 'An investigation conducted to provide stakeholders with information about the quality of the product or service under test');

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`tester`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (51, 3, 4);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (52, 2, 1);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (53, 3, 4);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (54, 3, 2);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (55, 5, 3);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (56, 5, 4);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (57, 4, 2);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (58, 4, 3);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (59, 1, 4);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (60, 4, 2);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (61, 5, 2);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (62, 2, 4);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (63, 3, 2);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (64, 5, 3);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (65, 4, 4);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (66, 3, 2);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (67, 4, 3);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (68, 3, 1);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (69, 3, 4);
INSERT INTO `minijira`.`tester` (`employee_id`, `test_type_id`, `rank_id`) VALUES (70, 3, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`customer_agent`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`customer_agent` (`employee_id`, `customer_id`) VALUES (91, 9);
INSERT INTO `minijira`.`customer_agent` (`employee_id`, `customer_id`) VALUES (92, 4);
INSERT INTO `minijira`.`customer_agent` (`employee_id`, `customer_id`) VALUES (93, 3);
INSERT INTO `minijira`.`customer_agent` (`employee_id`, `customer_id`) VALUES (94, 7);
INSERT INTO `minijira`.`customer_agent` (`employee_id`, `customer_id`) VALUES (95, 5);
INSERT INTO `minijira`.`customer_agent` (`employee_id`, `customer_id`) VALUES (96, 5);
INSERT INTO `minijira`.`customer_agent` (`employee_id`, `customer_id`) VALUES (97, 4);
INSERT INTO `minijira`.`customer_agent` (`employee_id`, `customer_id`) VALUES (98, 10);
INSERT INTO `minijira`.`customer_agent` (`employee_id`, `customer_id`) VALUES (99, 7);
INSERT INTO `minijira`.`customer_agent` (`employee_id`, `customer_id`) VALUES (100, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`project`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`project` (`project_id`, `project_type_id`, `customer_id`, `title`, `description`, `date_start`, `date_end`, `dev_lead_id`, `test_lead_id`, `pm_id`, `customer_agent_id`) VALUES (1, 2, 5, 'Volutpat Nulla Facilisis Limited', 'Lorem ipsum dolor sit amet,', '20.03.2011', '02.12.2013', 24, 70, 82, 93);
INSERT INTO `minijira`.`project` (`project_id`, `project_type_id`, `customer_id`, `title`, `description`, `date_start`, `date_end`, `dev_lead_id`, `test_lead_id`, `pm_id`, `customer_agent_id`) VALUES (2, 2, 6, 'Nisi Mauris Inc.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '19.10.2010', '13.08.2012', 3, 54, 89, 99);
INSERT INTO `minijira`.`project` (`project_id`, `project_type_id`, `customer_id`, `title`, `description`, `date_start`, `date_end`, `dev_lead_id`, `test_lead_id`, `pm_id`, `customer_agent_id`) VALUES (3, 1, 3, 'Elementum Sem Vitae Inc.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur', '28.05.2011', '19.01.2014', 28, 62, 84, 99);
INSERT INTO `minijira`.`project` (`project_id`, `project_type_id`, `customer_id`, `title`, `description`, `date_start`, `date_end`, `dev_lead_id`, `test_lead_id`, `pm_id`, `customer_agent_id`) VALUES (4, 1, 7, 'Et Ltd', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur', '19.04.2011', '04.03.2014', 15, 70, 89, 95);
INSERT INTO `minijira`.`project` (`project_id`, `project_type_id`, `customer_id`, `title`, `description`, `date_start`, `date_end`, `dev_lead_id`, `test_lead_id`, `pm_id`, `customer_agent_id`) VALUES (5, 1, 1, 'At LLP', 'Lorem ipsum dolor', '22.08.2011', '31.03.2014', 23, 60, 85, 93);
INSERT INTO `minijira`.`project` (`project_id`, `project_type_id`, `customer_id`, `title`, `description`, `date_start`, `date_end`, `dev_lead_id`, `test_lead_id`, `pm_id`, `customer_agent_id`) VALUES (6, 2, 6, 'Egestas Duis Institute', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '20.07.2010', '20.03.2012', 9, 56, 84, 96);
INSERT INTO `minijira`.`project` (`project_id`, `project_type_id`, `customer_id`, `title`, `description`, `date_start`, `date_end`, `dev_lead_id`, `test_lead_id`, `pm_id`, `customer_agent_id`) VALUES (7, 1, 4, 'Est Corp.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing', '03.05.2010', '08.06.2014', 15, 59, 87, 92);
INSERT INTO `minijira`.`project` (`project_id`, `project_type_id`, `customer_id`, `title`, `description`, `date_start`, `date_end`, `dev_lead_id`, `test_lead_id`, `pm_id`, `customer_agent_id`) VALUES (8, 5, 9, 'Aliquet Limited', 'Lorem ipsum dolor sit amet, consectetuer', '13.05.2011', '14.03.2014', 14, 70, 81, 98);
INSERT INTO `minijira`.`project` (`project_id`, `project_type_id`, `customer_id`, `title`, `description`, `date_start`, `date_end`, `dev_lead_id`, `test_lead_id`, `pm_id`, `customer_agent_id`) VALUES (9, 2, 5, 'Consectetuer Adipiscing Foundation', 'Lorem', '12.04.2011', '28.06.2014', 26, 68, 84, 99);
INSERT INTO `minijira`.`project` (`project_id`, `project_type_id`, `customer_id`, `title`, `description`, `date_start`, `date_end`, `dev_lead_id`, `test_lead_id`, `pm_id`, `customer_agent_id`) VALUES (10, 3, 5, 'Magna Industries', 'Lorem ipsum dolor sit', '16.03.2011', '18.11.2013', 14, 54, 88, 99);

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`task`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (1, 3, 4, 56, 48, 1, 'malesuada fames', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '22.02.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (2, 4, 3, 37, 49, 1, 'hendrerit a,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.', '28.05.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (3, 3, 4, 57, 23, 1, 'netus et', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.', '24.08.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (4, 1, 4, 29, 16, 1, 'nunc. Quisque', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '07.03.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (5, 3, 3, 22, 85, 6, 'diam eu', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', '16.02.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (6, 4, 1, 70, 96, 1, 'tellus. Phasellus', 'Lorem ipsum dolor sit amet, consectetuer', '11.01.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (7, 3, 4, 56, 56, 10, 'purus. Duis', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing', '03.02.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (8, 2, 1, 14, 56, 10, 'Donec egestas.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.', '15.07.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (9, 4, 3, 37, 35, 10, 'cursus. Integer', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna', '06.01.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (10, 1, 4, 54, 19, 5, 'facilisis. Suspendisse', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut', '29.07.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (11, 4, 3, 37, 79, 10, 'tempor diam', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna', '18.08.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (12, 1, 1, 70, 10, 2, 'Nulla dignissim.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '26.09.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (13, 1, 2, 18, 99, 4, 'sit amet', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '19.12.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (14, 4, 3, 50, 41, 5, 'a purus.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed', '01.06.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (15, 4, 4, 2, 99, 1, 'erat neque', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.', '15.11.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (16, 4, 2, 19, 97, 9, 'arcu et', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna', '26.06.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (17, 2, 1, 60, 74, 3, 'tristique neque', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur', '09.09.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (18, 1, 4, 39, 66, 5, 'et nunc.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '15.08.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (19, 4, 4, 29, 82, 8, 'ultrices, mauris', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu', '25.02.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (20, 2, 2, 40, 6, 4, 'dictum eleifend,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '22.06.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (21, 3, 4, 28, 76, 5, 'nascetur ridiculus', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '22.06.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (22, 3, 2, 22, 6, 4, 'dictum. Phasellus', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer', '21.09.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (23, 1, 4, 69, 85, 7, 'ut mi.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer', '24.06.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (24, 1, 1, 1, 55, 5, 'vulputate, lacus.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '11.06.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (25, 3, 3, 53, 16, 3, 'sociis natoque', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '13.02.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (26, 1, 1, 64, 45, 3, 'egestas ligula.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing', '17.09.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (27, 2, 3, 18, 96, 6, 'lobortis, nisi', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '09.08.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (28, 3, 4, 23, 18, 9, 'nascetur ridiculus', 'Lorem ipsum dolor sit amet, consectetuer adipiscing', '24.11.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (29, 1, 4, 52, 35, 6, 'Sed nulla', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '29.04.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (30, 3, 1, 7, 96, 5, 'eu neque', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed', '11.01.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (31, 2, 3, 50, 82, 3, 'tellus justo', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing', '07.05.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (32, 3, 4, 14, 37, 5, 'diam. Sed', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu', '12.04.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (33, 2, 3, 63, 85, 3, 'est, mollis', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer', '02.03.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (34, 1, 3, 57, 73, 3, 'gravida sagittis.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer', '09.02.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (35, 4, 1, 25, 5, 8, 'lobortis quis,', 'Lorem ipsum dolor sit amet, consectetuer', '21.08.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (36, 4, 2, 47, 31, 7, 'sit amet', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna', '03.01.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (37, 2, 3, 53, 49, 9, 'amet diam', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed', '08.03.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (38, 4, 1, 56, 10, 4, 'faucibus. Morbi', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.', '02.09.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (39, 2, 4, 8, 24, 10, 'eu, odio.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', '23.05.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (40, 2, 1, 8, 6, 9, 'Sed et', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu', '10.06.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (41, 3, 3, 40, 57, 10, 'erat, in', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.', '07.05.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (42, 1, 2, 60, 37, 8, 'semper, dui', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.', '02.08.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (43, 2, 3, 29, 44, 8, 'felis, adipiscing', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '28.10.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (44, 1, 2, 14, 95, 4, 'leo elementum', 'Lorem ipsum dolor sit amet,', '15.11.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (45, 4, 3, 43, 79, 5, 'lorem, luctus', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', '05.08.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (46, 3, 2, 65, 41, 9, 'ultricies dignissim', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing', '14.12.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (47, 4, 4, 57, 60, 10, 'mollis. Phasellus', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut', '27.02.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (48, 2, 4, 70, 73, 1, 'lacus. Quisque', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '21.01.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (49, 3, 2, 51, 10, 7, 'Class aptent', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.', '03.05.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (50, 3, 3, 50, 55, 10, 'Aliquam ornare,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '05.04.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (51, 2, 3, 29, 18, 8, 'sem ut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '18.08.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (52, 1, 1, 64, 17, 3, 'Curabitur vel', 'Lorem ipsum dolor sit amet, consectetuer', '17.10.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (53, 1, 4, 65, 66, 1, 'ante. Nunc', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu', '27.07.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (54, 1, 3, 20, 8, 10, 'amet lorem', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '22.03.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (55, 4, 2, 29, 71, 5, 'eget tincidunt', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '27.05.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (56, 4, 4, 48, 6, 5, 'Lorem ipsum', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '18.12.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (57, 3, 4, 12, 68, 4, 'ante. Vivamus', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.', '17.10.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (58, 4, 3, 44, 76, 9, 'sed dolor.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu', '01.03.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (59, 4, 2, 37, 22, 1, 'faucibus lectus,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut', '22.02.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (60, 1, 2, 63, 69, 2, 'dictum eu,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '13.06.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (61, 3, 2, 34, 99, 6, 'mi tempor', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', '17.04.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (62, 3, 1, 5, 46, 7, 'a, scelerisque', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur', '15.01.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (63, 2, 4, 46, 44, 2, 'Nullam feugiat', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur', '07.09.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (64, 4, 1, 7, 99, 10, 'ornare, libero', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '20.03.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (65, 3, 2, 37, 17, 2, 'pulvinar arcu', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer', '25.03.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (66, 1, 3, 12, 85, 6, 'risus varius', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur', '14.12.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (67, 1, 4, 30, 41, 4, 'Nulla eu', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '09.08.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (68, 4, 4, 7, 80, 2, 'Suspendisse commodo', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed', '26.06.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (69, 1, 4, 1, 27, 9, 'sodales nisi', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu', '25.04.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (70, 4, 1, 66, 55, 6, 'Nulla facilisi.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', '06.11.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (71, 1, 4, 14, 47, 7, 'adipiscing elit.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing', '09.11.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (72, 4, 3, 21, 6, 6, 'erat, eget', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '15.02.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (73, 2, 4, 66, 36, 4, 'massa. Vestibulum', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.', '27.12.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (74, 1, 2, 63, 62, 6, 'bibendum sed,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.', '02.01.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (75, 2, 3, 14, 2, 6, 'tortor, dictum', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', '17.03.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (76, 2, 4, 36, 1, 5, 'sollicitudin orci', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut', '28.10.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (77, 2, 3, 17, 68, 6, 'sem magna', 'Lorem ipsum dolor sit amet,', '23.01.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (78, 1, 4, 15, 62, 4, 'est ac', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed', '13.04.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (79, 1, 3, 51, 76, 4, 'sit amet', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '30.08.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (80, 1, 3, 55, 46, 2, 'dui. Fusce', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed', '08.04.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (81, 1, 3, 9, 65, 3, 'rhoncus id,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '14.05.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (82, 1, 2, 64, 41, 1, 'nisl sem,', 'Lorem ipsum dolor sit amet, consectetuer', '16.01.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (83, 4, 4, 68, 10, 1, 'mauris elit,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna', '10.10.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (84, 4, 3, 41, 3, 2, 'ac sem', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer', '28.06.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (85, 2, 3, 60, 75, 8, 'cursus. Integer', 'Lorem ipsum dolor sit amet,', '08.06.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (86, 3, 4, 13, 54, 2, 'Morbi sit', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '08.11.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (87, 1, 3, 4, 23, 9, 'ipsum nunc', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed', '18.07.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (88, 3, 4, 59, 66, 9, 'ornare lectus', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna', '19.05.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (89, 4, 3, 6, 61, 8, 'vulputate ullamcorper', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.', '27.01.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (90, 1, 3, 59, 23, 9, 'hendrerit id,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing', '17.01.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (91, 2, 1, 17, 20, 2, 'rutrum non,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu', '18.06.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (92, 2, 4, 24, 34, 2, 'augue ac', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.', '02.03.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (93, 4, 3, 43, 69, 9, 'consectetuer adipiscing', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu', '21.09.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (94, 3, 4, 10, 43, 4, 'ornare, facilisis', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.', '11.06.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (95, 3, 3, 62, 68, 3, 'Integer vulputate,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed', '10.11.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (96, 3, 3, 6, 84, 9, 'egestas lacinia.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna', '21.04.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (97, 2, 2, 33, 36, 5, 'varius orci,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur', '24.01.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (98, 1, 2, 29, 63, 8, 'et netus', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur', '12.02.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (99, 3, 1, 66, 78, 1, 'Aliquam ornare,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', '16.06.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (100, 4, 3, 43, 86, 4, 'bibendum. Donec', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.', '09.06.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (101, 1, 3, 6, 46, 8, 'tincidunt, neque', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '17.02.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (102, 4, 4, 30, 19, 9, 'mauris, rhoncus', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut', '14.11.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (103, 4, 2, 61, 27, 7, 'gravida. Aliquam', 'Lorem ipsum dolor sit amet,', '02.02.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (104, 3, 1, 62, 49, 3, 'nulla magna,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', '09.07.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (105, 2, 3, 55, 19, 1, 'lobortis ultrices.', 'Lorem ipsum dolor sit amet, consectetuer', '15.07.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (106, 3, 2, 11, 44, 7, 'nibh. Phasellus', 'Lorem ipsum dolor sit amet, consectetuer', '14.08.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (107, 1, 4, 48, 82, 9, 'Aliquam nisl.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed', '05.02.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (108, 4, 1, 65, 20, 1, 'dui quis', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', '04.08.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (109, 1, 2, 48, 10, 10, 'vulputate dui,', 'Lorem ipsum dolor sit amet,', '31.08.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (110, 1, 1, 1, 89, 1, 'fames ac', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur', '17.08.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (111, 4, 4, 7, 84, 7, 'semper egestas,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur', '31.08.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (112, 3, 1, 54, 66, 7, 'adipiscing. Mauris', 'Lorem ipsum dolor sit amet, consectetuer adipiscing', '07.10.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (113, 3, 4, 58, 57, 6, 'volutpat nunc', 'Lorem ipsum dolor sit amet, consectetuer adipiscing', '03.10.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (114, 1, 1, 51, 15, 3, 'et arcu', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.', '18.04.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (115, 4, 3, 3, 65, 10, 'ultrices a,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '19.12.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (116, 2, 4, 13, 15, 3, 'pede. Cum', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna', '12.05.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (117, 1, 3, 29, 74, 6, 'non, dapibus', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing', '28.03.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (118, 3, 2, 55, 89, 3, 'lorem semper', 'Lorem ipsum dolor sit amet, consectetuer', '20.04.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (119, 3, 1, 65, 63, 5, 'Donec est', 'Lorem ipsum dolor sit amet, consectetuer adipiscing', '06.06.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (120, 1, 2, 5, 40, 7, 'interdum. Nunc', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '22.11.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (121, 3, 3, 37, 18, 2, 'Nam interdum', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '04.11.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (122, 2, 3, 54, 66, 8, 'et netus', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '07.05.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (123, 4, 4, 8, 40, 10, 'sit amet', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed', '07.11.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (124, 3, 2, 36, 18, 4, 'Nullam vitae', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu', '03.08.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (125, 2, 2, 9, 62, 6, 'dictum mi,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer', '22.12.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (126, 3, 2, 41, 28, 2, 'blandit at,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '02.11.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (127, 2, 4, 33, 36, 1, 'eget, venenatis', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer', '24.06.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (128, 1, 4, 58, 49, 8, 'placerat eget,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.', '30.09.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (129, 2, 3, 54, 83, 5, 'lobortis. Class', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '28.11.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (130, 3, 2, 19, 63, 9, 'Duis at', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut', '24.03.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (131, 4, 1, 41, 97, 10, 'sapien. Nunc', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing', '29.05.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (132, 1, 2, 42, 76, 2, 'erat. Etiam', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '16.09.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (133, 3, 3, 21, 6, 10, 'et pede.', 'Lorem ipsum dolor sit amet,', '26.05.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (134, 2, 2, 6, 26, 4, 'consectetuer adipiscing', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer', '01.06.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (135, 4, 1, 10, 91, 1, 'scelerisque dui.', 'Lorem ipsum dolor sit amet,', '15.10.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (136, 1, 3, 62, 36, 7, 'sit amet,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '17.12.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (137, 2, 1, 19, 39, 2, 'iaculis enim,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna', '24.05.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (138, 3, 4, 55, 90, 8, 'risus quis', 'Lorem ipsum dolor sit amet, consectetuer', '14.10.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (139, 2, 1, 40, 41, 10, 'cursus in,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', '21.04.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (140, 1, 2, 33, 83, 2, 'ornare. In', 'Lorem ipsum dolor sit amet,', '30.10.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (141, 2, 2, 2, 51, 6, 'blandit. Nam', 'Lorem ipsum dolor sit amet,', '09.12.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (142, 4, 4, 5, 49, 1, 'Donec est', 'Lorem ipsum dolor sit amet, consectetuer adipiscing', '06.03.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (143, 2, 3, 56, 31, 3, 'lectus pede,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.', '12.01.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (144, 3, 4, 23, 88, 2, 'semper et,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', '17.02.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (145, 3, 3, 2, 22, 4, 'et malesuada', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.', '04.11.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (146, 4, 2, 47, 97, 6, 'imperdiet, erat', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed', '26.06.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (147, 3, 1, 17, 82, 5, 'lacus. Etiam', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur', '12.12.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (148, 3, 3, 2, 69, 10, 'ligula. Nullam', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '03.02.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (149, 1, 1, 57, 47, 3, 'malesuada ut,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.', '23.10.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (150, 3, 2, 11, 22, 1, 'sit amet,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing', '24.04.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (151, 3, 2, 30, 11, 1, 'Curae; Donec', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur', '03.07.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (152, 2, 2, 19, 32, 9, 'accumsan convallis,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '23.09.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (153, 3, 3, 25, 99, 10, 'nec, leo.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing', '02.10.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (154, 2, 2, 52, 37, 9, 'mollis lectus', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu', '03.06.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (155, 2, 2, 41, 32, 2, 'varius ultrices,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '23.03.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (156, 1, 1, 57, 93, 7, 'arcu vel', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '09.11.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (157, 2, 1, 20, 34, 2, 'est tempor', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna', '30.12.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (158, 4, 3, 53, 59, 5, 'ante ipsum', 'Lorem ipsum dolor sit amet,', '01.05.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (159, 1, 3, 6, 26, 5, 'pharetra ut,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut', '11.11.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (160, 1, 3, 34, 72, 3, 'molestie pharetra', 'Lorem ipsum dolor sit amet,', '18.06.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (161, 2, 2, 2, 57, 8, 'vulputate, risus', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu', '10.12.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (162, 2, 3, 5, 98, 5, 'dolor vitae', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut', '19.05.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (163, 4, 4, 58, 21, 8, 'malesuada. Integer', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '07.07.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (164, 1, 2, 42, 36, 6, 'tincidunt orci', 'Lorem ipsum dolor sit amet,', '05.06.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (165, 4, 1, 38, 61, 10, 'lectus rutrum', 'Lorem ipsum dolor sit amet, consectetuer', '23.12.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (166, 1, 1, 29, 64, 3, 'Integer vitae', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '14.03.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (167, 4, 1, 17, 72, 6, 'interdum libero', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing', '25.01.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (168, 1, 1, 3, 79, 8, 'non nisi.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '14.07.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (169, 1, 2, 18, 11, 8, 'ligula consectetuer', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', '03.02.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (170, 2, 4, 45, 77, 1, 'pede sagittis', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '17.08.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (171, 4, 1, 51, 44, 1, 'eget, ipsum.', 'Lorem ipsum dolor sit amet, consectetuer', '09.02.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (172, 2, 1, 4, 27, 3, 'at fringilla', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '12.04.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (173, 1, 1, 35, 91, 6, 'vestibulum massa', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '21.10.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (174, 4, 3, 54, 3, 4, 'ante, iaculis', 'Lorem ipsum dolor sit amet,', '15.04.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (175, 3, 4, 48, 56, 1, 'tellus sem', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna', '15.04.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (176, 4, 4, 60, 18, 7, 'interdum feugiat.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing', '24.06.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (177, 4, 1, 49, 45, 1, 'ac facilisis', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer', '24.06.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (178, 1, 1, 5, 54, 8, 'Sed nunc', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec', '02.02.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (179, 4, 2, 60, 96, 3, 'placerat, orci', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', '12.01.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (180, 3, 2, 26, 21, 2, 'Maecenas mi', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu', '07.10.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (181, 4, 3, 38, 8, 3, 'auctor vitae,', 'Lorem ipsum dolor sit amet, consectetuer', '08.08.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (182, 1, 1, 67, 92, 3, 'erat vel', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '07.11.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (183, 1, 1, 8, 45, 8, 'penatibus et', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.', '27.10.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (184, 1, 4, 32, 19, 10, 'vel est', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur', '16.08.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (185, 1, 4, 70, 40, 8, 'Donec egestas.', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna', '14.09.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (186, 2, 4, 62, 54, 6, 'interdum enim', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.', '09.08.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (187, 1, 4, 10, 88, 5, 'Etiam ligula', 'Lorem ipsum dolor sit amet, consectetuer adipiscing', '13.04.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (188, 4, 3, 46, 71, 1, 'mattis velit', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.', '01.11.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (189, 3, 3, 38, 52, 8, 'sodales at,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer', '14.04.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (190, 1, 2, 37, 63, 5, 'nonummy ut,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed', '04.09.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (191, 4, 2, 32, 43, 3, 'et, rutrum', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '28.01.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (192, 1, 3, 51, 19, 5, 'vitae, sodales', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed', '11.06.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (193, 4, 4, 51, 69, 3, 'turpis. Aliquam', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.', '17.10.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (194, 3, 1, 67, 98, 4, 'per inceptos', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', '26.02.2012', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (195, 3, 1, 57, 97, 6, 'lacinia orci,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu', '08.11.2012', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (196, 1, 3, 66, 73, 1, 'ad litora', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', '25.01.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (197, 1, 3, 49, 34, 9, 'Vivamus euismod', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.', '22.07.2013', 0);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (198, 3, 3, 28, 94, 9, 'dolor, nonummy', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed', '14.01.2013', 1);
INSERT INTO `minijira`.`task` (`task_id`, `priority_id`, `workflow_id`, `assignee_id`, `reporter_id`, `project_id`, `title`, `description`, `dueto`, `closed`) VALUES (199, 4, 1, 40, 54, 9, 'luctus. Curabitur', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur', '23.08.2013', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`project_tech_joint`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (10, 17);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (3, 5);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (9, 14);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (8, 13);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (9, 12);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (8, 5);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (1, 10);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (1, 4);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (10, 12);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (4, 9);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (10, 11);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (9, 7);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (2, 11);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (8, 3);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (9, 6);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (1, 15);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (4, 5);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (2, 3);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (1, 16);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (9, 16);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (7, 11);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (3, 12);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (2, 5);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (9, 2);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (10, 5);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (6, 14);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (9, 8);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (3, 1);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (1, 17);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (3, 17);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (4, 6);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (9, 11);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (6, 17);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (8, 12);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (6, 10);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (3, 14);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (3, 7);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (1, 2);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (5, 17);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (4, 2);
INSERT INTO `minijira`.`project_tech_joint` (`project_id`, `dev_tech_id`) VALUES (8, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`dev_tech_joint`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (22, 14, 8);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (7, 7, 10);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (11, 9, 4);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (39, 17, 1);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (37, 15, 6);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (40, 16, 2);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (37, 16, 1);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (28, 9, 10);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (18, 17, 1);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (5, 14, 10);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (8, 5, 8);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (44, 7, 2);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (40, 11, 6);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (8, 7, 6);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (9, 2, 5);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (38, 15, 4);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (45, 11, 3);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (45, 3, 8);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (41, 9, 7);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (41, 11, 5);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (25, 11, 1);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (31, 7, 7);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (11, 10, 1);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (38, 13, 2);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (9, 9, 1);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (26, 7, 7);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (37, 5, 8);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (22, 1, 3);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (5, 15, 9);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (29, 11, 7);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (14, 2, 3);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (19, 13, 7);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (3, 16, 3);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (4, 12, 10);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (11, 15, 5);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (11, 7, 8);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (42, 2, 1);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (31, 9, 1);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (40, 4, 6);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (22, 8, 7);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (35, 14, 4);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (19, 15, 3);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (30, 16, 10);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (28, 3, 9);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (49, 7, 2);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (38, 4, 3);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (39, 14, 8);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (43, 12, 4);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (40, 9, 6);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (18, 16, 1);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (1, 11, 9);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (19, 17, 7);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (32, 10, 7);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (30, 3, 8);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (20, 2, 2);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (28, 15, 4);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (29, 8, 9);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (49, 5, 4);
INSERT INTO `minijira`.`dev_tech_joint` (`developer_id`, `dev_tech_id`, `experience`) VALUES (27, 11, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (1, 55, 10, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (2, 31, 9, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (3, 61, 8, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (4, 82, 2, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (5, 81, 10, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (6, 56, 4, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (7, 1, 8, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (8, 61, 7, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (9, 8, 3, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (10, 76, 4, 'Lorem ipsum dolor sit amet, consectetuer');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (11, 61, 9, 'Lorem ipsum dolor sit amet,');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (12, 99, 2, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (13, 57, 8, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (14, 18, 6, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (15, 98, 4, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (16, 51, 10, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (17, 11, 10, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (18, 87, 6, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (19, 13, 7, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (20, 49, 4, 'Lorem ipsum dolor sit amet, consectetuer');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (21, 56, 10, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (22, 58, 10, 'Lorem ipsum dolor sit amet, consectetuer');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (23, 48, 6, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (24, 17, 8, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (25, 3, 7, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (26, 86, 2, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (27, 8, 3, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (28, 1, 2, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (29, 59, 3, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (30, 38, 8, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (31, 48, 4, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (32, 44, 9, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (33, 56, 4, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (34, 45, 6, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (35, 91, 6, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (36, 76, 5, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (37, 55, 5, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (38, 29, 5, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (39, 58, 1, 'Lorem ipsum dolor sit amet, consectetuer adipiscing');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (40, 50, 5, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (41, 80, 10, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (42, 8, 4, 'Lorem ipsum dolor sit amet, consectetuer');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (43, 34, 10, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (44, 14, 4, 'Lorem ipsum dolor sit amet,');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (45, 66, 3, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (46, 87, 1, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (47, 27, 6, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (48, 97, 9, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (49, 83, 5, 'Lorem ipsum dolor sit amet,');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (50, 97, 10, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (51, 88, 8, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (52, 11, 9, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (53, 23, 2, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (54, 72, 3, 'Lorem ipsum dolor sit amet, consectetuer');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (55, 75, 10, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (56, 33, 8, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (57, 33, 1, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (58, 82, 1, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (59, 80, 9, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (60, 86, 9, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (61, 36, 7, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (62, 80, 8, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (63, 60, 1, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (64, 33, 8, 'Lorem ipsum dolor sit amet, consectetuer adipiscing');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (65, 42, 5, 'Lorem ipsum dolor sit amet,');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (66, 79, 9, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (67, 19, 2, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (68, 16, 10, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (69, 99, 8, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (70, 26, 6, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (71, 46, 10, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (72, 38, 8, 'Lorem ipsum dolor sit amet, consectetuer');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (73, 10, 4, 'Lorem ipsum dolor sit amet, consectetuer');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (74, 96, 7, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (75, 9, 1, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (76, 15, 9, 'Lorem ipsum dolor sit amet, consectetuer adipiscing');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (77, 100, 1, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (78, 91, 2, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (79, 84, 2, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (80, 62, 5, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (81, 21, 9, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (82, 49, 3, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (83, 56, 4, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (84, 66, 3, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (85, 50, 4, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (86, 82, 6, 'Lorem ipsum dolor sit amet, consectetuer');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (87, 95, 6, 'Lorem ipsum dolor sit amet, consectetuer adipiscing');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (88, 21, 5, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (89, 42, 3, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (90, 37, 9, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (91, 90, 1, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (92, 38, 6, 'Lorem ipsum dolor sit amet,');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (93, 60, 2, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (94, 15, 8, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (95, 35, 3, 'Lorem ipsum dolor sit amet,');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (96, 62, 1, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (97, 73, 1, 'Lorem ipsum dolor sit amet, consectetuer');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (98, 88, 3, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (99, 21, 7, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec');
INSERT INTO `minijira`.`comment` (`comment_id`, `employee_id`, `project_id`, `text`) VALUES (100, 70, 1, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`project_employee_joint`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (88, 6, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (7, 2, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (62, 6, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (66, 10, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (3, 2, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (51, 9, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (78, 9, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (14, 9, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (87, 8, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (36, 2, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (53, 10, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (76, 1, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (68, 7, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (74, 4, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (47, 4, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (83, 9, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (98, 7, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (77, 8, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (51, 7, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (1, 10, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (93, 6, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (37, 3, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (91, 10, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (2, 2, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (24, 10, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (33, 10, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (27, 7, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (4, 2, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (60, 2, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (27, 2, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (93, 5, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (64, 4, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (2, 4, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (71, 4, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (74, 7, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (63, 10, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (14, 3, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (42, 3, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (32, 9, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (20, 2, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (59, 1, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (23, 7, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (80, 7, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (4, 4, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (41, 2, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (72, 8, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (96, 9, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (89, 4, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (2, 7, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (34, 9, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (79, 5, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (71, 7, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (1, 5, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (68, 5, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (71, 9, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (36, 6, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (94, 6, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (72, 5, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (76, 5, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (63, 8, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (64, 3, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (34, 3, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (72, 10, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (50, 7, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (40, 1, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (6, 10, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (61, 7, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (46, 5, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (93, 1, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (22, 7, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (90, 3, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (21, 7, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (96, 4, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (17, 9, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (24, 3, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (91, 3, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (11, 9, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (75, 4, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (84, 3, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (71, 5, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (14, 4, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (84, 6, 0);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (82, 6, 1);
INSERT INTO `minijira`.`project_employee_joint` (`employee_id`, `project_id`, `active`) VALUES (75, 8, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `minijira`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `minijira`;
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (1, 'user1', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (2, 'user2', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (3, 'user3', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (4, 'user4', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (5, 'user5', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (6, 'user6', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (7, 'user7', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (8, 'user8', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (9, 'user9', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (10, 'user10', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (11, 'user11', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (12, 'user12', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (13, 'user13', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (14, 'user14', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (15, 'user15', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (16, 'user16', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (17, 'user17', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (18, 'user18', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (19, 'user19', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (20, 'user20', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (21, 'user21', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (22, 'user22', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (23, 'user23', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (24, 'user24', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (25, 'user25', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (26, 'user26', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (27, 'user27', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (28, 'user28', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (29, 'user29', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (30, 'user30', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (31, 'user31', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (32, 'user32', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (33, 'user33', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (34, 'user34', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (35, 'user35', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (36, 'user36', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (37, 'user37', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (38, 'user38', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (39, 'user39', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (40, 'user40', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (41, 'user41', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (42, 'user42', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (43, 'user43', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (44, 'user44', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (45, 'user45', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (46, 'user46', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (47, 'user47', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (48, 'user48', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (49, 'user49', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (50, 'user50', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (51, 'user51', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (52, 'user52', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (53, 'user53', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (54, 'user54', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (55, 'user55', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (56, 'user56', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (57, 'user57', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (58, 'user58', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (59, 'user59', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (60, 'user60', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (61, 'user61', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (62, 'user62', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (63, 'user63', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (64, 'user64', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (65, 'user65', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (66, 'user66', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (67, 'user67', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (68, 'user68', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (69, 'user69', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (70, 'user70', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (71, 'user71', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (72, 'user72', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (73, 'user73', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (74, 'user74', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (75, 'user75', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (76, 'user76', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (77, 'user77', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (78, 'user78', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (79, 'user79', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (80, 'user80', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (81, 'user81', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (82, 'user82', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (83, 'user83', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (84, 'user84', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (85, 'user85', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (86, 'user86', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (87, 'user87', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (88, 'user88', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (89, 'user89', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (90, 'user90', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (91, 'user91', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (92, 'user92', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (93, 'user93', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (94, 'user94', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (95, 'user95', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (96, 'user96', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (97, 'user97', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (98, 'user98', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (99, 'user99', '123456');
INSERT INTO `minijira`.`user` (`employee_id`, `username`, `password`) VALUES (100, 'user100', '123456');

COMMIT;

USE `minijira`;

DELIMITER $$
USE `minijira`$$
$$

USE `minijira`$$
CREATE TRIGGER user_insert 
	BEFORE INSERT ON `user` 
	FOR EACH ROW SET NEW.password = PASSWORD(NEW.password);
$$


DELIMITER ;
