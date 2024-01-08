package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.entity.ScientificField;
import fon.mas.novica.Faculty.Project.repository.ScientificFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/scfield")
@RequiredArgsConstructor
public class ScientificFieldController {

    private final ScientificFieldRepository scientificFieldRepository;

    @PostMapping
    public ResponseEntity<?> createScientificField(@RequestBody ScientificField scientificField){
        return ResponseEntity.ok(scientificFieldRepository.save(scientificField));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(scientificFieldRepository.findAll());
    }
}
