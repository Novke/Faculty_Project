package fon.mas.novica.Faculty.Project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column
    String name;
    @Column(name = "short_name", unique = true)
    String shortName;
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    List<Subject> subjects;

}
