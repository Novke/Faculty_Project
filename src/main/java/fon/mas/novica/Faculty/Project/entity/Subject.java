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
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int espb;
    @JoinColumn(name = "department_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Department department;
    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Engagement> lecturers;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "subject", cascade = CascadeType.ALL)
    private LecturePlan lecturePlan;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    private Set<LectureSchedule> schedules;

    public void createLecturePlan(int pred, int vezbe, int lab){
        lecturePlan = new LecturePlan(this, vezbe, pred, lab);
    }
    public void setLecturePlan(LecturePlan lecturePlan){
        lecturePlan.setSubject(this);
        this.lecturePlan = lecturePlan;
    }
    public LectureSchedule getSchedule(int year){
        for (LectureSchedule s : schedules){
            if (s.getYear()==year) return s;
        }
        return null;
    }
}
