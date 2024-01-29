CREATE TABLE Subject (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    espb INT,
    department_id BIGINT,
    lecture_plan_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES Department(id)
);

