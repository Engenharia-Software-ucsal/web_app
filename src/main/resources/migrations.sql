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



CREATE VIEW employeesWithDependents as
SELECT e.cpf as employee_cpf,e.role as employee_role, e.id as employee_id, e.name as employee_name, d.id
             as dependent_id, d.name as dependent_name, d.cpf as dependent_cpf, d.employee_id as dependent_for
FROM employee e
         LEFT JOIN dependent d on e.id = d.employee_id ORDER BY  e.id;




SELECT  * FROM employeesWithDependents;

CREATE PROCEDURE updateEmployeeName(employee_id int, new_name varchar(100))
    LANGUAGE SQL AS
    $$
        UPDATE employee SET name = new_name WHERE id  = employee_id
    $$;




CALL updateEmployeeName(9, 'Genios magrao junior');