create procedure findCommentsByProject (IN in_project_id INT)
	select * from comment c where (project_id = in_project_id);

create procedure findProjectByTech (IN tech_id INT)
	select p.* from project p, project_tech_joint joint where p.project_id = joint.project_id 
								&& joint.dev_tech_id = tech_id;

create procedure findProjectByEmployee (IN employee_id INT)
	select p.* from project p, project_employee_joint joint where p.project_id = joint.project_id 
								&& joint.employee_id = employee_id;
