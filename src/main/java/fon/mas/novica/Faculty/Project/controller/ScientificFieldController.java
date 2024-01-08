package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.repository.ScientificFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/scfield")
@RequiredArgsConstructor
public class ScientificFieldController {

    private final ScientificFieldRepository scientificFieldRepository;
}
