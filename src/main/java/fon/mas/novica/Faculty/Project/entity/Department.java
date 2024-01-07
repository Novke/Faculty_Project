package fon.mas.novica.Faculty.Project.entity;

import jakarta.persistence.*;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column
    String name;
    @Column(name = "short_name", unique = true)
    String shortName;


}
