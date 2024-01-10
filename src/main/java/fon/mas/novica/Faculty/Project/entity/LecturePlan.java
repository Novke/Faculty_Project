package fon.mas.novica.Faculty.Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LecturePlan {

    @Id
    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Subject subject;
    private int vezbe;
    private int predavanja;
    private int laboratorija;

}
