create view user_role_v as
	select 
		u.email as user,
		r.rolename as role,
		u.password as pwd
	from

		user u inner join user_role ur on u.email=ur.user_id 
				inner join role r on ur.role_id = r.role_id;
		