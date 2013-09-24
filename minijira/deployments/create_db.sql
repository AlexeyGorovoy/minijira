SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

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
  `manager_type_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
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
-- Table `minijira`.`dev_rank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`dev_rank` (
  `dev_rank_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`dev_rank_id`))
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
  `main_tech_id` INT NOT NULL,
  `dev_rank_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  INDEX `fk_developer_employee1_idx` (`employee_id` ASC),
  PRIMARY KEY (`employee_id`),
  INDEX `fk_developer_dev_rank1_idx` (`dev_rank_id` ASC),
  INDEX `fk_developer_dev_tech1_idx` (`main_tech_id` ASC),
  CONSTRAINT `fk_developer_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `minijira`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_developer_dev_rank1`
    FOREIGN KEY (`dev_rank_id`)
    REFERENCES `minijira`.`dev_rank` (`dev_rank_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_developer_dev_tech1`
    FOREIGN KEY (`main_tech_id`)
    REFERENCES `minijira`.`dev_tech` (`dev_tech_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `minijira`.`test_rank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`test_rank` (
  `test_rank_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`test_rank_id`))
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
  `test_type_id` INT NOT NULL,
  `test_rank_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  PRIMARY KEY (`employee_id`),
  INDEX `fk_tester_test_rank1_idx` (`test_rank_id` ASC),
  INDEX `fk_tester_test_type1_idx` (`test_type_id` ASC),
  CONSTRAINT `fk_tester_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `minijira`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tester_test_rank1`
    FOREIGN KEY (`test_rank_id`)
    REFERENCES `minijira`.`test_rank` (`test_rank_id`)
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
  `customer_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  PRIMARY KEY (`employee_id`),
  CONSTRAINT `fk_customer_agent_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `minijira`.`employee` (`employee_id`)
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
  INDEX `fk_project_manager1_idx` (`pm_id` ASC),
  INDEX `fk_project_developer1_idx` (`dev_lead_id` ASC),
  INDEX `fk_project_tester1_idx` (`test_lead_id` ASC),
  INDEX `fk_project_customer_agent1_idx` (`customer_agent_id` ASC),
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
    REFERENCES `minijira`.`manager` (`manager_type_id`)
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
  `outdated` TINYINT(1) NULL,
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
-- Table `minijira`.`project_tech`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minijira`.`project_tech` (
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
  `dev_tech_id` INT NOT NULL,
  `developer_id` INT NOT NULL,
  `experience` DATE NOT NULL,
  PRIMARY KEY (`dev_tech_id`, `developer_id`),
  CONSTRAINT `fk_dev_tech_joint_developer1`
    FOREIGN KEY (`dev_tech_id`)
    REFERENCES `minijira`.`developer` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dev_tech_joint_dev_tech1`
    FOREIGN KEY (`dev_tech_id`)
    REFERENCES `minijira`.`dev_tech` (`dev_tech_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
