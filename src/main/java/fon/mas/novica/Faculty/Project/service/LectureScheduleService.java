package fon.mas.novica.Faculty.Project.service;

import fon.mas.novica.Faculty.Project.entity.LectureSchedule;
import fon.mas.novica.Faculty.Project.repository.LectureScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LectureScheduleService {

    private final LectureScheduleRepository lectureScheduleRepository;

    public LectureSchedule findById(Long id) throws FileNotFoundException {
        Optional<LectureSchedule> optionalLectureSchedule = lectureScheduleRepository.findById(id);
        if (optionalLectureSchedule.isPresent()){
            return optionalLectureSchedule.get();
        } else {
            throw new FileNotFoundException("Lecture Schedule with ID = " + id + " does not exist!");
        }
    }
}
