package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.entity.Department;
import fon.mas.novica.Faculty.Project.entity.Member;
import fon.mas.novica.Faculty.Project.service.DepartmentService;
import fon.mas.novica.Faculty.Project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final MemberService memberService;

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

    @PostMapping(path = "/{id}/manager")
    public ResponseEntity<?> assignNewManager(@RequestBody Member member, @PathVariable Long id) throws FileNotFoundException {
        Department department = departmentService.findById(id);
        Member vracenMember = memberService.findById(member.getId());
        departmentService.assignNewManager(department, vracenMember);
        return ResponseEntity.ok().build();
    }

}
