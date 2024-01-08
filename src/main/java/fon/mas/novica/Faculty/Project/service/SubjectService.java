package fon.mas.novica.Faculty.Project.service;

import fon.mas.novica.Faculty.Project.entity.Subject;
import fon.mas.novica.Faculty.Project.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

    public Subject findById(Long id) throws FileNotFoundException {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()){
            return optionalSubject.get();
        } else {
            throw new FileNotFoundException("Subject with ID = " + id + " does not exist!");
        }
    }

    public Subject create(Subject subject){
        return subjectRepository.save(subject);
    }

    public Subject edit(Subject subject) throws FileNotFoundException {
        findById(subject.getId());

        return subjectRepository.save(subject);
    }

    public void delete(Long subjectId) throws FileNotFoundException {
        findById(subjectId);

        subjectRepository.deleteById(subjectId);
    }
}
