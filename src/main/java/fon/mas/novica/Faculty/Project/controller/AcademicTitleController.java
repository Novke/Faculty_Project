package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.repository.AcademicTitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/actitle")
@RequiredArgsConstructor
public class AcademicTitleController {

    private final AcademicTitleRepository academicTitleRepository;
}
