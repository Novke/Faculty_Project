CREATE TABLE Secretary_Mandate (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  secretary_id BIGINT,
                                  department_id BIGINT,
                                  startDate DATE,
                                  endDate DATE,
                                  FOREIGN KEY (secretary_id) REFERENCES Member(id),
                                  FOREIGN KEY (department_id) REFERENCES Department(id)
);