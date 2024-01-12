package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.entity.Engagement;
import fon.mas.novica.Faculty.Project.entity.Lecture;
import fon.mas.novica.Faculty.Project.entity.Member;
import fon.mas.novica.Faculty.Project.entity.Subject;
import fon.mas.novica.Faculty.Project.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;

@RestController
@RequestMapping(path = "/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;
    private final EngagementService engagementService;
    private final SubjectService subjectService;
    private final MemberService memberService;
    private final LectureScheduleService lectureScheduleService;

    @GetMapping("/{lectureId}")
    public ResponseEntity<?> getSingleLecture(@PathVariable Long lectureId) throws FileNotFoundException {
        return ResponseEntity.ok(lectureService.findById(lectureId));
    }

    @PostMapping
    public ResponseEntity<?> createLecture(@RequestBody Lecture lecture) throws FileNotFoundException {
        if (lecture.getEngagement()!=null){
            lecture.setEngagement(engagementService.findById(lecture.getEngagement().getId()));
        } else {
            throw new IllegalArgumentException("Engagement not set for the lecture");
        }
        if (lecture.getSchedule()!=null){ //ne mora biti vezan za schedule
            lecture.setSchedule(lectureScheduleService.findById(lecture.getSchedule().getId()));
        }

        return ResponseEntity.ok(lectureService.create(lecture));
    }

    @PutMapping
    public ResponseEntity<?> editLecture(@RequestBody Lecture lecture) throws FileNotFoundException {
        lecture.setEngagement(engagementService.findById(lecture.getEngagement().getId()));

        return ResponseEntity.ok(lectureService.edit(lecture));
    }

    @PatchMapping("/{lectureId}")
    public ResponseEntity<?> updateDateTitle(@PathVariable Long lectureId,
                                             @RequestParam(name = "date", defaultValue = "") String datumString,
                                             @RequestParam(name = "time", defaultValue = "") String vremeString,
                                             @RequestParam(name = "content", defaultValue = "") String title) throws FileNotFoundException {
        Lecture lecture = lectureService.findById(lectureId);
        return ResponseEntity.ok(lectureService.updateDateTitle(lecture, datumString, vremeString, title));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLecture(@PathVariable Long id) throws FileNotFoundException {
        lectureService.findById(id);

        lectureService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/engagement/{id}")
    public ResponseEntity<?> findAllInEngagement(@PathVariable Long id) throws FileNotFoundException {
        Engagement engagement = engagementService.findById(id);
        return ResponseEntity.ok(lectureService.findAllInEngagement(engagement));
    }
    @GetMapping("/subject/{subjectId}/{year}")
    public ResponseEntity<?> findAllBySubjectInYear(@PathVariable Long subjectId,
                                                    @PathVariable int year) throws FileNotFoundException {
        Subject subject = subjectService.findById(subjectId);
        return ResponseEntity.ok(lectureService.findAllBySubjectInYear(subject, year));
    }
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<?> findAllBySubjectCurrentYear(@PathVariable Long subjectId) throws FileNotFoundException {
        Subject subject = subjectService.findById(subjectId);
        int year = LocalDate.now().getYear();
        return ResponseEntity.ok(lectureService.findAllBySubjectInYear(subject, year));
    }
    @GetMapping("/member/{memberId}/{year}")
    public ResponseEntity<?> findAllByMemberInYear(@PathVariable Long memberId,
                                                   @PathVariable int year) throws FileNotFoundException {
        Member member = memberService.findById(memberId);
        return ResponseEntity.ok(lectureService.findAllByMemberInYear(member, year));
    }
    @GetMapping("/member/{memberId}")
    public ResponseEntity<?> findAllByMemberCurrentYear(@PathVariable Long memberId) throws FileNotFoundException {
        Member member = memberService.findById(memberId);
        int year = LocalDate.now().getYear();
        return ResponseEntity.ok(lectureService.findAllByMemberInYear(member, year));
    }


}
