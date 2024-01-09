package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.entity.*;
import fon.mas.novica.Faculty.Project.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping(path = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final DepartmentService departmentService;
    private final AcademicTitleService academicTitleService;
    private final ScientificFieldService scientificFieldService;
    private final EducationTitleService educationTitleService;

    @GetMapping
    ResponseEntity<?> getAll(){
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<?> getMember(@PathVariable Long id) throws FileNotFoundException {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody Member member) throws FileNotFoundException {
        member.setDepartment(departmentService.findById(member.getDepartment().getId()));
        member.setAcademicTitle(academicTitleService.find(member.getAcademicTitle()));
        member.setEducationTitle(educationTitleService.find(member.getEducationTitle()));
        member.setScientificField(scientificFieldService.find(member.getScientificField()));

        return ResponseEntity.ok(memberService.create(member));
    }

    @PutMapping
    public ResponseEntity<?> editMember(@RequestBody Member member) throws FileNotFoundException {
        member.setDepartment(departmentService.findById(member.getDepartment().getId()));
        member.setAcademicTitle(academicTitleService.find(member.getAcademicTitle()));
        member.setEducationTitle(educationTitleService.find(member.getEducationTitle()));
        member.setScientificField(scientificFieldService.find(member.getScientificField()));

        return ResponseEntity.ok(memberService.edit(member));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) throws FileNotFoundException {
        memberService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/promote")
    public ResponseEntity<?> promoteMemberTitle(@RequestBody AcademicTitleHistory ac) throws FileNotFoundException {
        ScientificField vracenScField = scientificFieldService.find(ac.getScientificField());
        AcademicTitle vracenAcTitle = academicTitleService.find(ac.getAcademicTitle());
        Member vracenMember = memberService.findById(ac.getMember().getId());
        return ResponseEntity.ok(memberService.promoteAcademicTitle(vracenMember, vracenScField, vracenAcTitle));
    }
}
