package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.entity.Engagement;
import fon.mas.novica.Faculty.Project.service.EngagementService;
import fon.mas.novica.Faculty.Project.service.MemberService;
import fon.mas.novica.Faculty.Project.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping(path = "/engagements")
@RequiredArgsConstructor
public class EngagementController {

    private final EngagementService engagementService;
    private final SubjectService subjectService;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<?> createEngagement(@RequestBody Engagement engagement) throws FileNotFoundException {
        engagement.setMember(memberService.findById(engagement.getMember().getId()));
        engagement.setSubject(subjectService.findById(engagement.getSubject().getId()));

        return ResponseEntity.ok(engagementService.create(engagement));
    }
}
