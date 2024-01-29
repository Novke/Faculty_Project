CREATE TABLE Academic_Title_History (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      member_id BIGINT,
                                      startDate DATE,
                                      endDate DATE,
                                      academic_title BIGINT,
                                      scientific_field BIGINT,
                                      FOREIGN KEY (member_id) REFERENCES Member(id),
                                      FOREIGN KEY (academic_title) REFERENCES Academic_Title(id),
                                      FOREIGN KEY (scientific_field) REFERENCES Scientific_Field(id)
);