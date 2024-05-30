CREATE TYPE employee_role as ENUM ('PROVIDER', 'MANAGER');

CREATE TABLE IF NOT EXISTS employee(
                                       id SERIAL PRIMARY KEY,
                                       name VARCHAR(100) NOT NULL,
                                       cpf VARCHAR(11) NOT NULL,
                                       role employee_role NOT NULL
);


CREATE TABLE IF NOT EXISTS dependent(
                                        id  SERIAL PRIMARY KEY,
                                        name VARCHAR(100) NOT NULL,
                                        cpf VARCHAR(11) NOT NULL,
                                        employee_id INT
);

CREATE TABLE IF NOT EXISTS sector(
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
                                     description varchar(200) NOT NULL
);



CREATE TABLE IF NOT EXISTS project(
                                      id SERIAL PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL,
                                      sector_id int NOT NULL
);


CREATE TABLE IF NOT EXISTS employee_project (
                                                employee_id int,
                                                project_id int,
                                                date_init DATE,
                                                PRIMARY KEY  (employee_id, project_id)
);


ALTER TABLE  dependent
    ADD CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE  CASCADE;

ALTER TABLE  project
    ADD CONSTRAINT fk_sector_id FOREIGN KEY (sector_id) REFERENCES sector(id);

ALTER TABLE  employee_project
    ADD CONSTRAINT fk_employee_project FOREIGN KEY (employee_id) REFERENCES employee(id),
    ADD CONSTRAINT fk_project_project FOREIGN KEY (project_id) REFERENCES project(id);


