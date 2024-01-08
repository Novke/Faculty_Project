package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.entity.EducationTitle;
import fon.mas.novica.Faculty.Project.repository.EducationTitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/edtitle")
@RequiredArgsConstructor
public class EducationTitleController {

    private final EducationTitleRepository educationTitleRepository;

    @PostMapping
    public ResponseEntity<?> createEducationTitle(@RequestBody EducationTitle educationTitle){
        return ResponseEntity.ok(educationTitleRepository.save(educationTitle));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(educationTitleRepository.findAll());
    }

}
