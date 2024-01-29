package fon.mas.novica.Faculty.Project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecretaryMandate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Member secretary;
    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;
    private LocalDate startDate;
    private LocalDate endDate;
}
