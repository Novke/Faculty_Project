package fon.mas.novica.Faculty.Project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstname;
    private String lastname;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private AcademicTitle academicTitle;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private EducationTitle educationTitle;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private ScientificField scientificField;

    @JoinColumn(name = "department_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Department department;
}
