package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.entity.EducationTitle;
import fon.mas.novica.Faculty.Project.service.EducationTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/edtitle")
@RequiredArgsConstructor
public class EducationTitleController {

    private final EducationTitleService educationTitleService;

    @PostMapping
    public ResponseEntity<?> createEducationTitle(@RequestBody EducationTitle educationTitle){
        return ResponseEntity.ok(educationTitleService.save(educationTitle));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(educationTitleService.findAll());
    }

}
