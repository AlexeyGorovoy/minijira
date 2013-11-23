create procedure findCommentsByTask (IN in_task_id INT)
	select * from comment c where (task_id = in_task_id);

create procedure findProjectByTech (IN tech_id INT)
	select p.* from project p, project_tech_joint joint where p.project_id = joint.project_id 
								&& joint.dev_tech_id = tech_id;

create procedure findProjectByManagers (IN employee_id INT)
	select p.* from project p 
	where p.dev_lead_id = employee_id 
			or p.test_lead_id = employee_id 
			or p.pm_id = employee_id;

create procedure getPasswordHash 
	(IN pass_string VARCHAR(40), OUT hash_string VARCHAR(41) )
		return MD5(pass_string);