package fon.mas.novica.Faculty.Project.repository;

import fon.mas.novica.Faculty.Project.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
