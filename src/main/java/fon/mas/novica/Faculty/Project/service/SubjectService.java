package fon.mas.novica.Faculty.Project.service;

import fon.mas.novica.Faculty.Project.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
}
