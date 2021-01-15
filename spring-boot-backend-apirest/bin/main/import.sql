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
INSERT INTO users (id, username, password, is_enabled, name, last_name, email, create_at) VALUES (1, 'olga','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG', 1, 'olga', 'pérez', 'olgapc@gmail.com', NOW());
INSERT INTO users (id, username, password, is_enabled, name, last_name, email, create_at) VALUES (2, 'admin','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 1, 'admin', 'general', 'olgapc2@gmail.com', NOW());
/* POPULATE ROLES */
INSERT INTO roles (description, role, create_at) VALUES ('Usuari','ROLE_USER', NOW());
INSERT INTO roles (description, role, create_at) VALUES ('Administrador','ROLE_ADMIN', NOW());
/* POPULATE USERS ROLES */
/*INSERT INTO users_roles (user_fk, role_fk, create_at) VALUES (1, 1, NOW());*/
/*INSERT INTO users_roles (user_fk, role_fk, create_at) VALUES (2, 1, NOW());*/
/*INSERT INTO users_roles (user_fk, role_fk, create_at) VALUES (2, 2, NOW());*/
INSERT INTO users_roles (user_fk, role_fk) VALUES (1, 1);
INSERT INTO users_roles (user_fk, role_fk) VALUES (2, 1);
INSERT INTO users_roles (user_fk, role_fk) VALUES (2, 2);
/* POPULATE TASKS */
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('Primera tasca', 1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, employee_fk, is_done, is_maintask, maintask_fk, is_visible, is_periodically) VALUES ('Primera subtasca',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 1, 1, 0, 1, 0, 0);
INSERT INTO tasks (description, is_to_send, is_template, template_name, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, employee_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('Primera plantilla',  1, 1, 'Plantilla', 2, 'DAILY', '2020-12-31', NOW(), 1, 1, 0, 1, 0, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, employee_fk, is_done, is_maintask, maintask_fk, is_visible, is_periodically) VALUES ('Primera subtasca de plantilla',  1, 1, 2, 'DAILY', '2020-12-31', NOW(), 1, 1, 0, 0, 3, 0, 0);
INSERT INTO tasks (description, is_to_send, is_template, template_name, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, employee_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('Una altra plantilla 1',  1, 1, 'Plantilla 1', 2, 'DAILY', '2020-12-31', NOW(), 1, 1, 0, 1, 0, 0);
INSERT INTO tasks (description, is_to_send, is_template, template_name, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, employee_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('Una altra plantilla 2',  1, 1, 'Plantilla 2', 2, 'DAILY', '2020-12-31', NOW(), 1, 1, 0, 1, 0, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, employee_fk, is_done, is_maintask, maintask_fk, is_visible, is_periodically) VALUES ('Subtasca de plantilla 1',  1, 1, 2, 'DAILY', '2020-12-31', NOW(), 1, 1, 0, 0, 5, 0, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('Segona tasca sense treballador',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 2, 0, 1, 0, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, maintask_fk, is_visible, is_periodically) VALUES ('Primera subtasca de segona tasca',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 2, 0, 0, 8, 0, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, is_done, is_maintask, is_visible, is_periodically) VALUES ('Tercera tasca sense empresa',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 0, 1, 0, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, maintask_fk, is_visible, is_periodically) VALUES ('Primera subtasca de tercera tasca amb empresa diferent de tasca principal',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 2, 0, 0, 10, 1, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, maintask_fk, is_visible, is_periodically) VALUES ('Segona subtasca de tercera tasca amb empresa diferent de tasca principal',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 3, 0, 0, 10, 1, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, employee_fk, is_done, is_maintask, maintask_fk, is_visible, is_periodically) VALUES ('Primera subtasca de plantilla',  1, 1, 2, 'DAILY', '2020-12-31', NOW(), 3, 1, 0, 0, 3, 0, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('Una tasca principal',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('Una altra tasca principal',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('Revisar correu',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('Mirar telegram',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('Enviar activitats de telegram',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('Com com',  1, 0, 2, 'DAILY', '2020-12-31', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('El Sam no té ganes',  1, 0, 2, 'DAILY', '2020-12-30', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('Preparar bitly curt', 1, 0, 2, 'DAILY', '2020-12-29', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('Tasca principal per ara',  1, 0, 2, 'DAILY', '2020-12-3', NOW(), 1, 0, 1, 1, 0);
INSERT INTO tasks (description, is_to_send, is_template, number_to_calculate_deadline, type_calculation_deadline, deadline, create_at, company_fk, is_done, is_maintask, is_visible, is_periodically) VALUES ('Ultima tasca',  1, 0, 2, 'DAILY', '2021-12-31', NOW(), 1, 0, 1, 1, 0);
/* POPULATE TASK_INFORMATIONS */
INSERT INTO task_informations (information_fk, task_fk, create_at, comment, done, is_sticked) VALUES ('1', '1', NOW(), 'primer comentari', 0, 1);
INSERT INTO task_informations (information_fk, task_fk, create_at, comment, done, is_sticked) VALUES ('2', '1', NOW(), 'un altre comentari', 0, 0);