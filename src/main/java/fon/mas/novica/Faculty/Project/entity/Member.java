package fon.mas.novica.Faculty.Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Engagement> engagements;
    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AcademicTitleHistory> academicTitles;
}
