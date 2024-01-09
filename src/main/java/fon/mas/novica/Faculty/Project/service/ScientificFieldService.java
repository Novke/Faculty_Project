package fon.mas.novica.Faculty.Project.service;

import fon.mas.novica.Faculty.Project.entity.ScientificField;
import fon.mas.novica.Faculty.Project.repository.ScientificFieldRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScientificFieldService {

    private final ScientificFieldRepository scientificFieldRepository;

    public ScientificField save(ScientificField scientificField){
        return scientificFieldRepository.save(scientificField);
    }

    public List<ScientificField> findAll(){
        return scientificFieldRepository.findAll();
    }

    public ScientificField find(ScientificField scientificField) throws FileNotFoundException {
        if (scientificField == null) throw new IllegalArgumentException("Scientific field is null");

        Optional<ScientificField> optionalScientificField = scientificFieldRepository.findById(scientificField.getId());
        if (optionalScientificField.isPresent()){
            return optionalScientificField.get();
        } else {
            ScientificField field = scientificFieldRepository.findByName(scientificField.getName());
            if (field == null)
                throw new FileNotFoundException("Scientific field with ID = " + scientificField.getId() + " OR name = " + scientificField.getName() + " does not exist!");
            return field;
        }
    }

}
