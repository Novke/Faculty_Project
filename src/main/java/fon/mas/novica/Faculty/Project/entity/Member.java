package fon.mas.novica.Faculty.Project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberTitle> titleHistory;

    @JoinColumn(name = "department_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Department department;

    //TODO: check
    @Enumerated(EnumType.STRING)
    private Set<LectureForm> canLecture;
}
