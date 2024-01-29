CREATE TABLE Lecture_Plan (
             subject_id BIGINT PRIMARY KEY,
             vezbe INT,
             predavanja INT,
             laboratorija INT,
             FOREIGN KEY (subject_id) REFERENCES Subject(id)
);