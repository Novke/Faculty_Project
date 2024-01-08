package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.repository.EducationTitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/edtitle")
@RequiredArgsConstructor
public class EducationTitleController {

    private final EducationTitleRepository educationTitleRepository;

}
