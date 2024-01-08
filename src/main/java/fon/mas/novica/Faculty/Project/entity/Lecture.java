package fon.mas.novica.Faculty.Project.entity;

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
    @ManyToOne(fetch = FetchType.EAGER)
    private Engagement engagement;
    private LocalDateTime dateTime;
    private String subject;

}
