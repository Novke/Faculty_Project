package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.entity.Department;
import fon.mas.novica.Faculty.Project.entity.Subject;
import fon.mas.novica.Faculty.Project.service.DepartmentService;
import fon.mas.novica.Faculty.Project.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping(path = "/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final DepartmentService departmentService;

    @GetMapping
    ResponseEntity<?> getAll(){
        return ResponseEntity.ok(subjectService.findAll());
    }
    @GetMapping(path = "/{id}")
    ResponseEntity<?> getSubject(@PathVariable Long id) throws FileNotFoundException {
        return ResponseEntity.ok(subjectService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createSubject(@RequestBody Subject subject) throws FileNotFoundException {
        Department department = departmentService.findById(subject.getDepartment().getId()); //baca exc ako ne postoji dept
        subject.setDepartment(department);

        return ResponseEntity.ok(subjectService.create(subject));
    }

    @PutMapping
    public ResponseEntity<?> editSubject(@RequestBody Subject subject) throws FileNotFoundException {
        Department department = departmentService.findById(subject.getDepartment().getId()); //baca exc ako ne postoji dept
        subject.setDepartment(department);

        return ResponseEntity.ok(subjectService.edit(subject));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id) throws FileNotFoundException {
        subjectService.delete(id);
        return ResponseEntity.ok().build();
    }
}
