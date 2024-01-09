package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.entity.ScientificField;
import fon.mas.novica.Faculty.Project.service.ScientificFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/scfield")
@RequiredArgsConstructor
public class ScientificFieldController {

    private final ScientificFieldService scientificFieldService;

    @PostMapping
    public ResponseEntity<?> createScientificField(@RequestBody ScientificField scientificField){
        return ResponseEntity.ok(scientificFieldService.save(scientificField));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(scientificFieldService.findAll());
    }
}
