package fon.mas.novica.Faculty.Project.repository;

import fon.mas.novica.Faculty.Project.entity.SecretaryMandate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretaryMandateRepository extends JpaRepository<SecretaryMandate, Long> {
}
