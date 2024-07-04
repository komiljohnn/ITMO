CREATE TABLE Person(person_id serial PRIMARY KEY, name varchar(50) CHECK ( CHAR_LENGTH(name) > 1) NOT NULL, interests varchar(300) NOT NULL);

INSERT INTO Person (person_id, name, interests) VALUES (DEFAULT, 'Итания', 'Интересы были более эстетического направления');

CREATE TABLE Composition(person_id int REFERENCES Per-son(person_id),composition_id serial PRIMARY KEY,name varchar(50) CHECK ( CHAR_LENGTH(name) > 1) NOT NULL,tools varchar(200) NOT NULL);

INSERT INTO Composition(composition_id, name, tools) VALUES (DEFAULT, 'она изобретала переплетающиеся', 'С помощью синтезаторов материи ');

INSERT INTO Composition(composition_id, name, tools) VALUES (DEFAULT, 'были уже не просто', 'стереометрические конструкции');

CREATE TABLE Building(building_id serial PRIMARY KEY,name varchar(50) CHECK ( CHAR_LENGTH(name) > 3 ) NOT NULL,dimension varchar(50) NOT NULL);

INSERT INTO Building(building_id, name, dimension) VALUES (DEFAULT, 'хореогра-фических залах', 'гигантских');

CREATE TABLE Dance(dance_id serial PRIMARY KEY,composition_basis_id int REFER-ENCES Composition(composition_id),name varchar(50) CHECK ( CHAR_LENGTH(name) >  1 ) NOT NULL);

INSERT INTO Dance(dace_id,composition_basis_id, name) VALUES (DEFAULT, 1, 'танцевальных вариаций');

CREATE TABLE Floor(floor_id serial PRIMARY KEY,composition_id int REFERENCES Composition(composition_id),building_id int REFERENCES Build-ing(building_id),name varchar(50) CHECK ( CHAR_LENGTH(name) > 2) NOT NULL);

INSERT INTO Floor(floor_id, composition_id, building_id, name) VALUES (DE-FAULT,1,1,'пола');

CREATE TABLE Comparison(id serial PRIMARY KEY,composition1_id int REFERENCES Composition(composition_id),composition2_id int REFERENCES Composi-tion(composition_id));

INSERT INTO Comparison(id,composition1_id,composition2_id) VALUES (DEFAULT, 1, 2);

CREATE TABLE Type_of_composition(type_of_composition_id serial PRIMARY KEY, composition_id int REFERENCES Composition(composition_id),level varchar(100) NOT NULL,view varchar(300) NOT NULL,complexity varchar(100) NOT NULL);

INSERT INTO Type_of_composition(type_of_composition_id, composition_id, level, view,complexity) VALUES (DEFAULT, 1, 'трехмерные структуры',
                                                                                                'такой красоты',
                                                                                       'сложности');
INSERT INTO Type_of_composition(type_of_composition_id, composition_id, level, "view", complexity) VALUES (DEFAULT, 2, 'высшего',
                                                                                                           'топологические', 'высшего ' ||
                                                                                                                             'порядка');
