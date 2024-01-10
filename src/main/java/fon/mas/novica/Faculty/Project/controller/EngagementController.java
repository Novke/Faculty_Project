package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.entity.Department;
import fon.mas.novica.Faculty.Project.entity.Engagement;
import fon.mas.novica.Faculty.Project.entity.Member;
import fon.mas.novica.Faculty.Project.entity.Subject;
import fon.mas.novica.Faculty.Project.service.DepartmentService;
import fon.mas.novica.Faculty.Project.service.EngagementService;
import fon.mas.novica.Faculty.Project.service.MemberService;
import fon.mas.novica.Faculty.Project.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;

@RestController
@RequestMapping(path = "/engagements")
@RequiredArgsConstructor
public class EngagementController {

    private final EngagementService engagementService;
    private final SubjectService subjectService;
    private final MemberService memberService;
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<?> createEngagement(@RequestBody Engagement engagement) throws FileNotFoundException {
        engagement.setMember(memberService.findById(engagement.getMember().getId()));
        engagement.setSubject(subjectService.findById(engagement.getSubject().getId()));

        return ResponseEntity.ok(engagementService.create(engagement));
    }

    @PutMapping
    public ResponseEntity<?> editEngagement(@RequestBody Engagement engagement) throws FileNotFoundException{
        engagement.setMember(memberService.findById(engagement.getMember().getId()));
        engagement.setSubject(subjectService.findById(engagement.getSubject().getId()));

        return ResponseEntity.ok(engagementService.edit(engagement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEngagement(@PathVariable Long id) throws FileNotFoundException {
        engagementService.findById(id);

        engagementService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/dept/{departmentId}/{year}")
    public ResponseEntity<?> findAllByDepartmentAndYear(@PathVariable Long departmentId,
                                                        @PathVariable int year) throws FileNotFoundException {
        Department department = departmentService.findById(departmentId);

        return ResponseEntity.ok(engagementService.findAllByDepartmentAndYear(department, year));
    }
    @GetMapping("/dept/{departmentId}")
    public ResponseEntity<?> findAllByDepartmentCurrentYear(@PathVariable Long departmentId) throws FileNotFoundException {
        Department department = departmentService.findById(departmentId);
        int year = LocalDate.now().getYear();

        return ResponseEntity.ok(engagementService.findAllByDepartmentAndYear(department, year));
    }

    @GetMapping("/memb/{memberId}/{year}")
    public ResponseEntity<?> findAllByMemberAndYear(@PathVariable Long memberId,
                                                    @PathVariable int year) throws FileNotFoundException {
        Member member = memberService.findById(memberId);

        return ResponseEntity.ok(engagementService.findAllByMemberAndYear(member, year));
    }
    @GetMapping("/memb/{memberId}")
    public ResponseEntity<?> findAllByMemberCurrentYear(@PathVariable Long memberId) throws FileNotFoundException {
        Member member = memberService.findById(memberId);
        int year = LocalDate.now().getYear();

        return ResponseEntity.ok(engagementService.findAllByMemberAndYear(member, year));
    }
    @GetMapping("/subj/{subjectId}/{year}")
    public ResponseEntity<?> findAllBySubjectAndYear(@PathVariable Long subjectId,
                                                    @PathVariable int year) throws FileNotFoundException {
        Subject subject = subjectService.findById(subjectId);

        return ResponseEntity.ok(engagementService.findAllBySubjectAndYear(subject, year));
    }
    @GetMapping("/subj/{subjectId}")
    public ResponseEntity<?> findAllBySubjectCurrentYear(@PathVariable Long subjectId) throws FileNotFoundException {
        Subject subject = subjectService.findById(subjectId);
        int year = LocalDate.now().getYear();

        return ResponseEntity.ok(engagementService.findAllBySubjectAndYear(subject, year));
    }

    @GetMapping("/subj/{subjectId}/memb/{memberId}/{year}")
    public ResponseEntity<?> findAllBySubjectAndMemberAndYear(@PathVariable Long subjectId,
                                                              @PathVariable Long memberId,
                                                              @PathVariable int year) throws FileNotFoundException {
        Subject subject = subjectService.findById(subjectId);
        Member member = memberService.findById(memberId);

        return ResponseEntity.ok(engagementService.findAllBySubjectMemberYear(subject, member, year));
    }
    @GetMapping("/subj/{subjectId}/memb/{memberId}")
    public ResponseEntity<?> findAllBySubjectAndMemberCurrentYear(@PathVariable Long subjectId,
                                                              @PathVariable Long memberId) throws FileNotFoundException {
        Subject subject = subjectService.findById(subjectId);
        Member member = memberService.findById(memberId);
        int year = LocalDate.now().getYear();

        return ResponseEntity.ok(engagementService.findAllBySubjectMemberYear(subject, member, year));
    }
}
