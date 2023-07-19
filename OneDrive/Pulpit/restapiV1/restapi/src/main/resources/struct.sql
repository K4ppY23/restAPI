-- Tworzenie bazy danych

CREATE DATABASE Uczelnia;

USE Uczelnia;

-- Tworzenie tabeli Studenci

CREATE TABLE Students (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    age INT(3) NOT NULL,
    mainSubject VARCHAR(50) NOT NULL

);

-- Tworzenie tabeli Wykładowcy

CREATE TABLE Learners (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    age INT(3) NOT NULL,
    department VARCHAR(50) NOT NULL

);

-- Tworzenie tabeli Przedmioty

CREATE TABLE Subjects (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL

);

-- Tworzenie tabeli łącznikowej Przypisania

CREATE TABLE Assignments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    learnerID INT NOT NULL,
    studentID INT NOT NULL,
    subjectID INT NOT NULL,
    FOREIGN KEY (learnerID) REFERENCES Learners(id),
    FOREIGN KEY (studentID) REFERENCES Students(id),
    FOREIGN KEY (subjectID) REFERENCES Subjects(id),

);
