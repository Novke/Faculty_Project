CREATE TABLE Lecture_Schedule (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 subject_id BIGINT,
                                 year INT NOT NULL,
                                 FOREIGN KEY (subject_id) REFERENCES Subject(id)
);