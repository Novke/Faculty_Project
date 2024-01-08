package fon.mas.novica.Faculty.Project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ScientificField {


    @Id
    @GeneratedValue
    long id;
    String name;
}
