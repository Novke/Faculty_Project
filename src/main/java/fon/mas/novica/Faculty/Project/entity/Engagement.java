package fon.mas.novica.Faculty.Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Engagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;
    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;
    private int year;
    //TODO: check
    @Enumerated(EnumType.ORDINAL)
    private Set<LectureForm> lectureForms;
    @OneToMany(mappedBy = "engagement", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Lecture> lectures;
}
