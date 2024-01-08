package fon.mas.novica.Faculty.Project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Entity
@Data
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;
    int espb;
    @JoinColumn(name = "department_id")
    @ManyToOne(fetch = FetchType.EAGER)
    Department department;
}
