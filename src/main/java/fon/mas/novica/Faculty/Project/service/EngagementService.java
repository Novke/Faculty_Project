package fon.mas.novica.Faculty.Project.service;

import fon.mas.novica.Faculty.Project.entity.Department;
import fon.mas.novica.Faculty.Project.entity.Engagement;
import fon.mas.novica.Faculty.Project.entity.Member;
import fon.mas.novica.Faculty.Project.entity.Subject;
import fon.mas.novica.Faculty.Project.repository.EngagementRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EngagementService {

    private final EngagementRepository engagementRepository;


    public List<Engagement> findAll(){
        return engagementRepository.findAll();
    }

    public Engagement findById(Long id) throws FileNotFoundException {
        Optional<Engagement> optionalEngagement = engagementRepository.findById(id);
        if (optionalEngagement.isPresent()){
            return optionalEngagement.get();
        } else {
            throw new FileNotFoundException("Engagement with ID = " + id + " does not exist!");
        }
    }


    public Engagement create(Engagement engagement){
        return engagementRepository.save(engagement);
    }

    public Engagement edit(Engagement engagement) throws FileNotFoundException {
        findById(engagement.getId());

        return engagementRepository.save(engagement);
    }

    public void delete(Long engagementId) throws FileNotFoundException {
        Engagement engagement = findById(engagementId);

        engagementRepository.delete(engagement);
    }

    public List<Engagement> findAllByMemberAndYear(Member member, int year){
        return engagementRepository.findAllByMemberAndYear(member, year);
    }

    public List<Engagement> findAllBySubjectAndYear(Subject subject, int year){
        return engagementRepository.findAllBySubjectAndYear(subject, year);
    }

    public List<Engagement> findAllBySubjectMemberYear(Subject subject, Member member, int year){
        return engagementRepository.findAllBySubjectAndMemberAndYear(subject, member, year);
    }

    public List<Engagement> findAllByDepartmentAndYear(Department department, int year){
        return engagementRepository.findAllBySubjectDepartmentAndYear(department, year);
    }
}
