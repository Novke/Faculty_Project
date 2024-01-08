package fon.mas.novica.Faculty.Project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @Column(name = "short_name", unique = true)
    private String shortName;
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Subject> subjects;
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Member> members;

}
