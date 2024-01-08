package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

}
