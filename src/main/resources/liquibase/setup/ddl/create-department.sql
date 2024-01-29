CREATE TABLE Department (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255),
                            short_name VARCHAR(255) UNIQUE,
                            manager_id BIGINT,
                            secretary_id BIGINT
--                             FOREIGN KEY (manager_id) REFERENCES Member(id),
--                             FOREIGN KEY (secretary_id) REFERENCES Member(id)
);