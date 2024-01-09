package fon.mas.novica.Faculty.Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Subject> subjects;
    @JsonIgnore
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Member> members;
    @OneToOne
    private Member manager;
    @OneToOne
    private Member secretary;
    @OneToMany(fetch = FetchType.LAZY)
    private List<SecretaryMandate> secretaryHistory;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ManagerMandate> managerHistory;

}
