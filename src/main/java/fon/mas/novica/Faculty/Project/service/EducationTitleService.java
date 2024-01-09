package fon.mas.novica.Faculty.Project.service;

import fon.mas.novica.Faculty.Project.entity.EducationTitle;
import fon.mas.novica.Faculty.Project.repository.EducationTitleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EducationTitleService {

    private final EducationTitleRepository educationTitleRepository;

    public EducationTitle save(EducationTitle educationTitle){
        return educationTitleRepository.save(educationTitle);
    }

    public List<EducationTitle> findAll(){
        return educationTitleRepository.findAll();
    }

    public EducationTitle find(EducationTitle educationTitle) throws FileNotFoundException {
        if (educationTitle == null) throw new IllegalArgumentException("Scientific Field is null");

        Optional<EducationTitle> optionalScientificField = educationTitleRepository.findById(educationTitle.getId());
        if (optionalScientificField.isPresent()){
            return optionalScientificField.get();
        } else {
            EducationTitle title = educationTitleRepository.findByName(educationTitle.getName());
            if (title == null)
                throw new FileNotFoundException("Education title with ID = " + educationTitle.getId() + " OR name = " + educationTitle.getName() + " does not exist!");
            return title;
        }
    }
}
