package fon.mas.novica.Faculty.Project.service;

import fon.mas.novica.Faculty.Project.entity.AcademicTitle;
import fon.mas.novica.Faculty.Project.repository.AcademicTitleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AcademicTitleService {

    private final AcademicTitleRepository academicTitleRepository;

    public AcademicTitle save(AcademicTitle academicTitle) {
        return academicTitleRepository.save(academicTitle);
    }

    public List<AcademicTitle> findAll() {
        return academicTitleRepository.findAll();
    }

    public AcademicTitle find(AcademicTitle academicTitle) throws FileNotFoundException {
        if (academicTitle == null) throw new IllegalArgumentException("Academic title is null");

        Optional<AcademicTitle> optionalAcademicTitle = academicTitleRepository.findById(academicTitle.getId());
        if (optionalAcademicTitle.isPresent()) {
            return optionalAcademicTitle.get();
        } else {
            AcademicTitle title = academicTitleRepository.findByName(academicTitle.getName());
            if (title == null)
                throw new FileNotFoundException("Academic Title with ID = " + academicTitle.getId() + " OR name = " + academicTitle.getName() + " does not exist!");
            return title;
        }
    }
}
