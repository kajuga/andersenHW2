INSERT INTO team (team_name) VALUES
('Shitikhin'),
('Sinoverskaia');

INSERT INTO employee (first_name, last_name, middle_name, short_name,
                      email, phone_number, birthday, date_of_start, dev_level, english_level, skype, team_id) VALUES
('Kos', 'Shitikhin', 'Andreevich', 'K.S.A', 'kos@gmail.com', '8-999-99-99','20-12-1994', '20-12-2010', 5, 4, 'kos@skype', 1),
('Sergey', 'Bobrovsiy', 'Andreevich', 'S.A.B', 'bob72@gmail.com', '8-989-98-98', '11-04-1985','01-03-2012',4, 4, 'bob72@Skype', 1),
('Vladimir', 'Tarasenko', 'Andreevich', 'V.A.T', 'tarasenko91@gmail.com', '8-979-97-97', '11-04-1985', '05-06-2013', 3, 3, 'taras91@skype', 1),
('Artemiy', 'Panarin', 'Sergeevich', 'A.S.P', 'panarabread10@gmail.com', '8-969-96-96', '11-04-1985', '04-06-2015', 1, 2, 'panara10@skype', 1),
('Kiril', 'Kaprizov', 'Olegovich', 'K.O.K', 'kaprizov97@gmail.com', '8-959-95-95', '11-04-1985', '23-08-2020', 1, 2, 'kaprizov97@skype', 1),
('Maxim', 'Kizilov', 'Sergeevich', 'M.S.K', 'maxkizi@yandex.ru', '8-949-94-94', '11-04-1985', '05-06-2021', 0, 2, 'maxkizi@skype', 1);

INSERT INTO employee (first_name, last_name, middle_name, short_name,
                      email, phone_number, birthday, date_of_start, dev_level, english_level, skype, team_id) VALUES
('Sydney', 'Crosby', 'Patrick', 'S.P.C', 'crosby87@gmail.com', '8-111-11-11', '11-04-1985',  '05-10-2005', 5, 5, 'crosby87@skype', 2),
('Mark-Anderea', 'Fleury', '-', 'M.A.F', 'flower29@gmail.com', '8-121-12-12', '11-04-1985', '11-12-2003',4, 5, 'flower29@Skype', 2),
('Patrick', 'Kane', 'Timothy', 'P.T.K', 'kane88gmail.com', '8-131-13-13', '11-04-1985','08-12-2007', 2, 5, 'kane88@skype', 2),
('Brent', 'Burns', 'William', 'B.W.B', 'chewbacca88@gmail.com', '8-141-14-14', '11-04-1985', '21-11-2011', 1, 5, 'chewbacca@skype', 2),
('Connor', 'McDavid', '-', 'C.M', 'mcdavid97@gmail.com', '8-151-15-15', '11-04-1985', '16-05-2016', 1, 5, 'mcdavid97@skype', 2),
('Alexis', 'Lafreniere', '-', 'A.L', 'lafreniere13@gmail.ru', '8-161-16-16','11-04-1985', '25-02-2021', 0, 5, 'lafreniere13@skype', 2);

INSERT INTO project(name_of_project, customer, duration, methodology, project_manager_id, team_id) VALUES
('Hockey', 'FFHS', 'hok', 'cwedd', 1, 1 ),
('Foot', 'RFS', 'foot', 'qwerty', 7, 2 );

INSERT INTO feed_back(description, date_of_fb, employee_id) VALUES
('bla bla bla', '11-04-2021', 2),
('wtf', '11-02-2021', 8),
('how...', '01-04-2021', 12),
('Hello...', '20-03-2021', 3),
('Dear...', '12-04-2021', 10),
('la la la', '20-12-2020', 9),
('...', '30-12-2020', 5);
