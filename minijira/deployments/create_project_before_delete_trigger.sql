USE `minijira`;
DELIMITER $$

CREATE TRIGGER project_before_delete BEFORE DELETE ON `project` FOR EACH ROW
begin
	
	delete from task where project_id = OLD.project_id;

end;
