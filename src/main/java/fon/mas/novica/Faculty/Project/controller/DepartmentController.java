package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.entity.Department;
import fon.mas.novica.Faculty.Project.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<?> getDepartment(@PathVariable Long id) throws FileNotFoundException {
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department){
        return ResponseEntity.ok(departmentService.create(department));
    }

    @PutMapping
    public ResponseEntity<?> editDepartment(@RequestBody Department department) throws FileNotFoundException {
        return ResponseEntity.ok(departmentService.edit(department));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) throws FileNotFoundException {
        departmentService.delete(id);
        return ResponseEntity.ok().build();
    }

}
