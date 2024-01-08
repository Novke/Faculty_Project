package fon.mas.novica.Faculty.Project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class MemberTitle {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;
    @ManyToOne(fetch = FetchType.EAGER)
    private AcademicTitle academicTitle;
    @ManyToOne(fetch = FetchType.EAGER)
    private ScientificField scientificField;

    private LocalDate startDate;
    private LocalDate endDate;
}
