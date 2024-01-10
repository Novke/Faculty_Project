package fon.mas.novica.Faculty.Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Lecture {

    @Id
    @GeneratedValue
    private long id;

    private LectureForm format;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Engagement engagement;
    private LocalDateTime dateTime;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private LectureSchedule schedule;

    public Member getTeacher(){
        return engagement.getMember();
    }
    public Subject getSubject(){
        return engagement.getSubject();
    }

}
