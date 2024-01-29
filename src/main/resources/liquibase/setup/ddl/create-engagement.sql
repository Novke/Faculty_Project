CREATE TABLE Engagement (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            member_id BIGINT,
                            subject_id BIGINT,
                            year INT,
                            lectureForms VARCHAR(255),
                            FOREIGN KEY (member_id) REFERENCES Member(id),
                            FOREIGN KEY (subject_id) REFERENCES Subject(id)
);