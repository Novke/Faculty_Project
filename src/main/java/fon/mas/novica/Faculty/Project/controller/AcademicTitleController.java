package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.entity.AcademicTitle;
import fon.mas.novica.Faculty.Project.repository.AcademicTitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/actitle")
@RequiredArgsConstructor
public class AcademicTitleController {

    private final AcademicTitleRepository academicTitleRepository;

    @PostMapping
    public ResponseEntity<?> createAcademicTitle(@RequestBody AcademicTitle academicTitle){
        return ResponseEntity.ok(academicTitleRepository.save(academicTitle));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(academicTitleRepository.findAll());
    }
}
