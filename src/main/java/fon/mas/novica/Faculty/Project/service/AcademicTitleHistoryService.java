package fon.mas.novica.Faculty.Project.service;

import fon.mas.novica.Faculty.Project.repository.AcademicTitleHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AcademicTitleHistoryService {

    private final AcademicTitleHistoryRepository academicTitleHistoryrepository;

}
