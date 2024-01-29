CREATE TABLE Manager_Mandate (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                manager_id BIGINT,
                                department_id BIGINT,
                                start_Date DATE,
                                end_Date DATE,
                                FOREIGN KEY (manager_id) REFERENCES Member(id),
                                FOREIGN KEY (department_id) REFERENCES Department(id)
);