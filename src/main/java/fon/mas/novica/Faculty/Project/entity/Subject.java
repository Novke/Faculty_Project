package fon.mas.novica.Faculty.Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fon.mas.novica.Faculty.Project.serialization.serializers.SubjectSerializer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonSerialize(using = SubjectSerializer.class)
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private long id;
    @EqualsAndHashCode.Include
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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<LectureSchedule> schedules;

    public void createLecturePlan(int pred, int vezbe, int lab){
        lecturePlan = new LecturePlan(this, vezbe, pred, lab);
    }
    public void setLecturePlan(LecturePlan lecturePlan){
        lecturePlan.setSubject(this);
        this.lecturePlan = lecturePlan;
    }
    public LectureSchedule getLatestSchedule(int year){
        for (LectureSchedule s : schedules){
            if (s.getYear()==year) return s;
        }
        return null;
    }

    public LectureSchedule getLatestSchedule(){
        int max = 0;
        LectureSchedule schedule = null;
        for (LectureSchedule s : schedules){
            if (s.getYear()>max){
                schedule = s;
            }
        }
        return schedule;
    }

    public void setSchedule(LectureSchedule newSchedule){
        newSchedule.setSubject(this);
        schedules.remove(newSchedule);
        schedules.add(newSchedule);
    }
}
