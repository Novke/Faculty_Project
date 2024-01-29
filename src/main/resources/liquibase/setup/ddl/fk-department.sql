ALTER TABLE Department
ADD FOREIGN KEY (manager_id) REFERENCES Member(id),
ADD FOREIGN KEY (secretary_id) REFERENCES Member(id);