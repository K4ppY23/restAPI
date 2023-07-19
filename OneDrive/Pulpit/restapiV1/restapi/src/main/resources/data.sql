USE Uczelnia;


-- Wprowadzenie danych do tabel

INSERT INTO Students(id,name,surname,age,mainSubject)
VALUES (1,'Jacek','Kowalski',19,'Informatyka'),
		(2,'Jan','Nowak',21,'Matematyka'),
        (3,'Bartosz','Cieszyński',20,'Fizyka');


INSERT INTO Learners(id,name,surname,age,department)
VALUES (1,'Zbigniew','Leszczyński',45,'Informatyka'),
		(2,'Waldemar','Kret',52,'Matematyka'),
        (3,'Aneta','Mieczyńska',37,'Fizyka'),
        (4,'Agata','Dzik',43,'Matematyka');




INSERT INTO Subjects(id,name)
VALUES (1,'Algebra Liniowa'),
		(2,'Fizyka Kwantowa'),
        (3,'Programowanie Obiektowe');




INSERT INTO Assignments(learnerID,studentID,subjectID)
VALUES (1,1,3),
        (2,2,1),
        (3,3,2),
        (4,3,1),
        (2,1,1);

