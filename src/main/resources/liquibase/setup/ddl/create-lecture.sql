CREATE TABLE Lecture (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         format VARCHAR(255),
                         engagement_id BIGINT,
                         date_Time TIMESTAMP,
                         title VARCHAR(255),
                         schedule_id BIGINT,
                         FOREIGN KEY (engagement_id) REFERENCES Engagement(id),
                         FOREIGN KEY (schedule_id) REFERENCES Lecture_Schedule(id)
);