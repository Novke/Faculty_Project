CREATE TABLE Member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    academic_title_id BIGINT,
    education_title_id BIGINT,
    scientific_field_id BIGINT,
    department_id BIGINT,
    FOREIGN KEY (academic_title_id) REFERENCES Academic_Title(id),
    FOREIGN KEY (education_title_id) REFERENCES Education_Title(id),
    FOREIGN KEY (scientific_field_id) REFERENCES Scientific_Field(id),
    FOREIGN KEY (department_id) REFERENCES Department(id)
);
