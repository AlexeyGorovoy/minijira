USE minijira_db;

INSERT INTO customers (title, description) VALUES ('Oracle', 'One of world\'s biggest software corporations.');
INSERT INTO customers (title, description) VALUES ('Zeliboba Corp.', 'World famous kids apps developer\publisher.');

INSERT INTO people (name, surname, mail, skype, site, password) VALUES ('Alexey', 'Gorovoy', 'alexey@gorovoy.com', 'alexey', 'gorovoy.com', 'passA');
INSERT INTO people (name, surname, mail, skype, site, password) VALUES ('Alexander', 'Savitski', 'alex@gmail.com', 'sanek1991211', 'vk.com', 'passB');

INSERT INTO projects (title, description, leader_id, customer_id) VALUES ('GameOfLife', 'Online gaming project.', 2, 2);

INSERT INTO skills (title, description) VALUES ('EJB3', '');
INSERT INTO skills (title, description) VALUES ('JSF', '');
INSERT INTO skills (title, description) VALUES ('Twitter Bootstrap', '');
INSERT INTO skills (title, description) VALUES ('JPA', '');
INSERT INTO skills (title, description) VALUES ('Facelets', '');

INSERT INTO used_skills (project_id, skill_id) VALUES (1, 1);
INSERT INTO used_skills (project_id, skill_id) VALUES (1, 2);
INSERT INTO used_skills (project_id, skill_id) VALUES (1, 3);
INSERT INTO used_skills (project_id, skill_id) VALUES (1, 5);

INSERT INTO people_skills (person_id, skill_id) VALUES (1, 1);
INSERT INTO people_skills (person_id, skill_id) VALUES (1, 4);
INSERT INTO people_skills (person_id, skill_id) VALUES (2, 2);
INSERT INTO people_skills (person_id, skill_id) VALUES (2, 3);
INSERT INTO people_skills (person_id, skill_id) VALUES (2, 5);

SELECT p.surname, s.title FROM people p, skills s, people_skills ps WHERE p.id = ps.person_id AND s.id = ps.skill_id;