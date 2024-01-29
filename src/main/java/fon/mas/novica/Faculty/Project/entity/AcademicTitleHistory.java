package fon.mas.novica.Faculty.Project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class AcademicTitleHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Member member;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "academic_title")
    private AcademicTitle academicTitle;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "scientific_field")
    private ScientificField scientificField;

    public AcademicTitleHistory(Member member, AcademicTitle academicTitle, ScientificField scientificField){
        this.member = member;
        this.academicTitle = academicTitle;
        this.scientificField = scientificField;
        startDate = LocalDate.now();
        endDate = null;
    }
}
