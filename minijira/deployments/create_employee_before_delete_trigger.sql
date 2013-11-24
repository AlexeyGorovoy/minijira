USE `minijira`;
DELIMITER $$

CREATE TRIGGER employee_before_delete BEFORE DELETE ON `employee` FOR EACH ROW
begin
	declare dummy INT;
	
	delete from user_role where user_id = OLD.email;
	delete from user where email = OLD.email;

	delete from manager where employee_id = OLD.employee_id;
	delete from developer where employee_id = OLD.employee_id;
	delete from tester where employee_id = OLD.employee_id;

	select employee_id
	into dummy
	from employee em inner join user_role ur on em.email = 'dummy@company.com' limit 1;
	
	update task set assignee_id = dummy 
					where assignee_id = OLD.employee_id;
	update task set reporter_id = dummy
					where reporter_id = OLD.employee_id;
			
	update project set dev_lead_id = dummy
					where dev_lead_id = OLD.employee_id;
	update project set test_lead_id = dummy
					where test_lead_id = OLD.employee_id;
	update project set pm_id = dummy
					where pm_id = OLD.employee_id;

end;
