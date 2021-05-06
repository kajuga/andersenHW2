DROP TABLE IF EXISTS feed_back CASCADE;
DROP TABLE IF EXISTS project CASCADE;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS team  CASCADE;

CREATE TABLE team(
                     team_id SERIAl,
                     team_name varchar (20) not null,
                     PRIMARY KEY (team_id)
);


CREATE TABLE employee(
                         employee_id SERIAL,
                         first_name varchar (50)     not null ,
                         last_name varchar (50)     not null ,
                         middle_name varchar (50)   not null,
                         short_name varchar (10),
                         email varchar (50),
                         phone_number varchar (50),
                         birthday      date         not null,
                         date_of_start date         not null,
                         dev_level int              not null ,
                         english_level int          not null,
                         skype varchar (50),
                         team_id int,
                         PRIMARY KEY (employee_id),
                         FOREIGN KEY (team_id) REFERENCES team (team_id) ON DELETE CASCADE
);

CREATE  TABLE project(
                         project_id         SERIAL,
                         name_of_project    varchar (50)         not null ,
                         customer           varchar (50),
                         duration           varchar (50)         not null,
                         methodology        varchar (50),
                         project_manager_id int,
                         team_id            int,
                         PRIMARY KEY (project_id),
                         FOREIGN KEY (project_manager_id) REFERENCES employee (employee_id) ON DELETE CASCADE ,
                         FOREIGN KEY (team_id) REFERENCES team (team_id) ON DELETE CASCADE
);


CREATE TABLE  feed_back(
                           feed_back_id SERIAL,
                           description varchar(100) not null,
                           date_of_fb date not null,
                           employee_id int,
                           PRIMARY KEY (feed_back_id),
                           FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
);