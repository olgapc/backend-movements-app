/* POPULATE COMPANY_TYPES */
INSERT INTO company_types (id, code, description) VALUES (1, 'S.L.', 'Societat Limitada');
INSERT INTO company_types (id, code, description) VALUES (2, 'S.A.', 'Societat Anònima');
INSERT INTO company_types (id, code, description) VALUES (3, 'S.C.P.', 'Societat Civil Privada');
/* POPULATE COMPANY*/
/*INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (1, 'Empresa1', 1, NOW() , 'mail@empresa1.com', '931234567', '');*/
/*INSERT INTO companies (id, company_name, company_type_fk, company_type_fk, create_at, email, phone, image) VALUES (2, 'Empresa2', 2, NOW() , 'info@empresa2.com', '939876543', '');*/
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (1, 'Empresa1', 1, NOW() , 'mail@empresa1.com', '931234567', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (2, 'Empresa2', 2, NOW() , 'info@empresa2.com', '939876543', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (3, 'Empresa3', 3, NOW() , 'mail@empresa3.com', '931234567', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (4, 'Empresa4', 1, NOW() , 'info@empresa4.com', '939876543', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (5, 'Empresa5', 2, NOW() , 'mail@empresa5.com', '931234567', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (6, 'Empresa6', 3, NOW() , 'info@empresa6.com', '939876543', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (7, 'Empresa7', 1, NOW() , 'mail@empresa7.com', '931234567', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (8, 'Empresa8', 1, NOW() , 'info@empresa8.com', '939876543', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (9, 'Empresa9', 1, NOW() , 'mail@empresa9.com', '931234567', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (10, 'Empresa10', 1, NOW() , 'info@empres10.com', '939876543', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (11, 'Empresa11', 2, NOW() , 'info@empres10.com', '939876543', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (12, 'Empresa12', 2, NOW() , 'info@empres10.com', '939876543', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (13, 'Empresa13', 2, NOW() , 'info@empres10.com', '939876543', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (14, 'Empresa14', 2, NOW() , 'info@empres10.com', '939876543', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (15, 'Empresa15', 2, NOW() , 'info@empres10.com', '939876543', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (16, 'Empresa16', 2, NOW() , 'info@empres10.com', '939876543', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (17, 'Empresa17', 3, NOW() , 'info@empres10.com', '939876543', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (18, 'Empresa18', 3, NOW() , 'info@empres10.com', '939876543', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (19, 'Empresa19', 3, NOW() , 'info@empres10.com', '939876543', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (20, 'Empresa20', 3, NOW() , 'info@empres10.com', '939876543', '');
INSERT INTO companies (id, company_name, company_type_fk, create_at, email, phone, image) VALUES (21, 'EmpresA21', 3, NOW() , 'info@empres10.com', '939876543', '');
/* POPULATE INFORMATION */
INSERT INTO informations (description, create_at) VALUES ('Duració contracte', NOW());
INSERT INTO informations (description, create_at) VALUES ('Irpf anual', NOW());
INSERT INTO informations (description, create_at) VALUES ('Dades treballador', NOW());
INSERT INTO informations (description, create_at) VALUES ('Data naixement', NOW());
INSERT INTO informations (description, create_at) VALUES ('Tipus contracte', NOW());
INSERT INTO informations (description, create_at) VALUES ('Horari', NOW());
INSERT INTO informations (description, create_at) VALUES ('Fi contracte', NOW());
INSERT INTO informations (description, create_at) VALUES ('Periode de prova', NOW());
INSERT INTO informations (description, create_at) VALUES ('Tipus acomiadament', NOW());
INSERT INTO informations (description, create_at) VALUES ('Motiu acomiadament', NOW());
INSERT INTO informations (description, create_at) VALUES ('Causes sanció', NOW());
INSERT INTO informations (description, create_at) VALUES ('Dies absència', NOW());
/* POPULATE EMPLOYEE */
INSERT INTO employees (employee_name, nif, naf, email, phone, create_at, company_fk, nif_type) VALUES ('Primer treballador', '46768707R', '08-1234567-09', 'olgapc@gmail.com', '83563563', NOW(), 1, 'DNI');
INSERT INTO employees (employee_name, nif, naf, email, phone, create_at, company_fk, nif_type) VALUES ('Segon treballador', '46768708W', '08-1234567-09', 'olgapc24@gmail.com', '677773', NOW(), 1, 'DNI');
INSERT INTO employees (employee_name, nif, naf, email, phone, create_at, company_fk, nif_type) VALUES ('Perico Pérez Fernández', '46768709A', '08-1234567-09', 'olgapc257@gmail.com', '677773', NOW(), 2, 'DNI');
INSERT INTO employees (employee_name, nif, naf, email, phone, create_at, company_fk, nif_type) VALUES ('María Francisca General Individua', '46768707R', '08-1234567-09', 'olgapc72@gmail.com', '677773', NOW(), 2, 'DNI');
INSERT INTO employees (employee_name, nif, naf, email, phone, create_at, company_fk, nif_type) VALUES ('Lucía Gómez Yuste', '46768708W', '08-1234567-09', 'olgapc772@gmail.com', '677773', NOW(), 2, 'DNI');
INSERT INTO employees (employee_name, nif, naf, email, phone, create_at, company_fk, nif_type) VALUES ('Paco Ramírez Esteban', '46768709A', '08-1234567-09', 'olgapc28@gmail.com', '677773', NOW(), 2, 'DNI');
INSERT INTO employees (employee_name, nif, naf, email, phone, create_at, company_fk, nif_type) VALUES ('Julio Escobar Flores', '46768707R', '08-1234567-09', 'olgapc289@gmail.com', '677773', NOW(), 2, 'DNI');
INSERT INTO employees (employee_name, nif, naf, email, phone, create_at, company_fk, nif_type) VALUES ('Juan Marco Apolo', '46768708W', '08-1234567-09', 'olgapc362@gmail.com', '677773', NOW(), 2, 'DNI');
INSERT INTO employees (employee_name, nif, naf, email, phone, create_at, company_fk, nif_type) VALUES ('Sofía Esteban Jurado', 'Y6768709A', '08-1234567-09', 'olgapctu2@gmail.com', '677773', NOW(), 2, 'NIE');
INSERT INTO employees (employee_name, nif, naf, email, phone, create_at, company_fk, nif_type) VALUES ('Maite Esteban Jurado', 'AAEF68708W', '08-1234567-09', 'olgapctu2@gmail.com', '677773', NOW(), 4, 'PASSPORT');
/* POPULATE USERS */
INSERT INTO users (id, username, password, is_enabled, name, last_name, email, create_at) VALUES ('dbb55244-327f-4d27-ae53-0bb4e1b8e9d2','olga','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG', 1, 'olga', 'pérez', 'olgapc@gmail.com', NOW());
INSERT INTO users (id, username, password, is_enabled, name, last_name, email, create_at) VALUES ('a3c5fad4-ec74-4bfa-b9dd-13d00820f6c7', 'admin','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 1, 'admin', 'general', 'olgapc2@gmail.com', NOW());
/* POPULATE ROLES */
INSERT INTO roles (description, role, create_at) VALUES ('Usuari','ROLE_USER', NOW());
INSERT INTO roles (description, role, create_at) VALUES ('Administrador','ROLE_ADMIN', NOW());
/* POPULATE USERS ROLES */
/*INSERT INTO users_roles (user_fk, role_fk, create_at) VALUES ('5d47708c7e2947a9b88c26593099cc94', 1, NOW());*/
/*INSERT INTO users_roles (user_fk, role_fk, create_at) VALUES ('68dfe3aa376c442ebad6f81ed76559d0', 1, NOW());*/
/*INSERT INTO users_roles (user_fk, role_fk, create_at) VALUES ('68dfe3aa376c442ebad6f81ed76559d0', 2, NOW());*/
INSERT INTO users_roles (user_fk, role_fk) VALUES ('dbb55244-327f-4d27-ae53-0bb4e1b8e9d2', 1);
INSERT INTO users_roles (user_fk, role_fk) VALUES ('a3c5fad4-ec74-4bfa-b9dd-13d00820f6c7', 1);
INSERT INTO users_roles (user_fk, role_fk) VALUES ('a3c5fad4-ec74-4bfa-b9dd-13d00820f6c7', 2);
/* POPULATE TASKS */
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('e3b144bc-4aab-4ec7-b1ed-120270c0e288', 'Primera tasca', 1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, employee_fk, is_done, is_maintask, maintask_fk, is_visible, is_periodically) VALUES ('663e3f65-a53f-4e13-987b-adad8d79d84e', 'Primera subtasca',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 1, 1, 0, 'e3b144bc-4aab-4ec7-b1ed-120270c0e288', 0, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, employee_fk, is_done, is_maintask, maintask_fk, is_visible, is_periodically) VALUES ('973576c9-8944-4669-940e-c5aecde47066', 'Segona subtasca de tasca1',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 1, 1, 0, 'e3b144bc-4aab-4ec7-b1ed-120270c0e288', 0, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, template_name, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, employee_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('50653faa-7003-491a-98a2-fc1bf561dfbe', 'Primera plantilla',  1, 1, 'Plantilla', 2, 'DAILY', '2020-12-31', NOW(), 1, 1, 0, 1, 0, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, employee_fk, is_done, is_maintask, maintask_fk, is_visible, is_periodically) VALUES ('c851738f-2693-4551-a6ea-02b142fde52c', 'Primera subtasca de plantilla',  1, 1, 2, 'DAILY', '2020-12-31', NOW(), 1, 1, 0, 0, '50653faa-7003-491a-98a2-fc1bf561dfbe', 0, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, template_name, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, employee_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('f5f9eb35-de63-4357-acc3-242a2c235725', 'Una altra plantilla 1',  1, 1, 'Plantilla 1', 2, 'DAILY', '2020-12-31', NOW(), 1, 1, 0, 1, 0, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, template_name, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, employee_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('f42674d2-0381-4add-bfbd-194649102c1b', 'Una altra plantilla 2',  1, 1, 'Plantilla 2', 2, 'DAILY', '2020-12-31', NOW(), 1, 1, 0, 1, 0, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, employee_fk, is_done, is_maintask, maintask_fk, is_visible, is_periodically) VALUES ('de26d7f5-d8a6-4ed0-a689-0bcdb8943e3b', 'Subtasca de plantilla 1',  1, 1, 2, 'DAILY', '2020-12-31', NOW(), 1, 1, 0, 0, 'f5f9eb35-de63-4357-acc3-242a2c235725', 0, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('f5b54e1f-6634-4321-b3ee-7198dec01a2b', 'Segona tasca sense treballador',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 2, 0, 1, 0, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, maintask_fk, is_visible, is_periodically) VALUES ('bb4f6145-5bf1-4ba5-bc83-75e5d328e14a', 'Primera subtasca de segona tasca',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 2, 0, 0, 'de26d7f5-d8a6-4ed0-a689-0bcdb8943e3b', 0, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, is_done, is_maintask, is_visible, is_periodically) VALUES ('54c6b273-03ab-41b4-ab7c-03e22d7ceaa9', 'Tercera tasca sense empresa',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 0, 1, 0, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, maintask_fk, is_visible, is_periodically) VALUES ('a295dde5-4ac1-4db4-99a3-711ce4839d0e', 'Primera subtasca de tercera tasca amb empresa diferent de tasca principal',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 2, 0, 0, '54c6b273-03ab-41b4-ab7c-03e22d7ceaa9', 1, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, maintask_fk, is_visible, is_periodically) VALUES ('3cfc3b9c-3d6b-40f6-b358-001cd152b091', 'Segona subtasca de tercera tasca amb empresa diferent de tasca principal',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 3, 0, 0, '54c6b273-03ab-41b4-ab7c-03e22d7ceaa9', 1, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, employee_fk, is_done, is_maintask, maintask_fk, is_visible, is_periodically) VALUES ('d5993fcf-7d50-497b-9482-6bee303d3a4e', 'Primera subtasca de plantilla',  1, 1, 2, 'DAILY', '2020-12-31', NOW(), 3, 1, 0, 0, '973576c9-8944-4669-940e-c5aecde47066', 0, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('e5f11501-36d8-4d06-95c8-332556cfdf86', 'Una tasca principal',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('a59de1a7-b841-4378-9de6-eb00fd5b873f', 'Una altra tasca principal',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('dfa1605a-2920-4045-9644-cf3dc23c78bb', 'Revisar correu',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('1859ffe1-9ddb-468f-a60c-d14aaaec2fc6', 'Mirar telegram',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('1eac762c-33db-4559-85ec-e775c7dc7c61', 'Enviar activitats de telegram',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('2b3899cf-6316-44cc-aa77-8eb681020f86', 'Com com',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('ce8058c8-7182-43fe-80f2-6c54f705e462', 'El Sam no té ganes',  1, 0, 2, 'DAILY', '2020-12-30', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('ca35e57c-26d1-4bf2-ad2f-6ab9dfbc083d', 'Preparar bitly curt', 1, 0, 2, 'DAILY', '2020-12-29', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('357568cf-9596-4c2c-8193-913e9e7edae0', 'Tasca principal per ara',  1, 0, 2, 'DAILY', '2020-12-3', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (id, description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('b9e61f12-e2ec-49de-af95-d532f300e13f','Ultima tasca',  1, 0, 2, 'DAILY', '2021-12-31', NOW(), 1, 0, 1, 1, 0);
/* POPULATE TASK_INFORMATIONS */
INSERT INTO task_informations (information_fk, task_fk, create_at, comment, done, is_sticked) VALUES ('1', 'e3b144bc-4aab-4ec7-b1ed-120270c0e288', NOW(), 'primer comentari', 0, 1);
INSERT INTO task_informations (information_fk, task_fk, create_at, comment, done, is_sticked) VALUES ('2', 'e3b144bc-4aab-4ec7-b1ed-120270c0e288', NOW(), 'un altre comentari', 0, 0);
/* POPULATE TASK_SEQUENCES */
INSERT INTO task_sequences (subtask_fk, pretask_fk, create_at, comment) VALUES ('663e3f65-a53f-4e13-987b-adad8d79d84e', 'e3b144bc-4aab-4ec7-b1ed-120270c0e288', NOW(), 'subtasca de la tasca 1');
INSERT INTO task_sequences (subtask_fk, pretask_fk, create_at, comment) VALUES ('973576c9-8944-4669-940e-c5aecde47066', 'e3b144bc-4aab-4ec7-b1ed-120270c0e288', NOW(), 'subtasca2 de la tasca 1');