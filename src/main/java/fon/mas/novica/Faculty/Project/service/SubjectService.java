package fon.mas.novica.Faculty.Project.service;

import fon.mas.novica.Faculty.Project.entity.*;
import fon.mas.novica.Faculty.Project.repository.EngagementRepository;
import fon.mas.novica.Faculty.Project.repository.LectureRepository;
import fon.mas.novica.Faculty.Project.repository.LectureScheduleRepository;
import fon.mas.novica.Faculty.Project.repository.SubjectRepository;
import fon.mas.novica.Faculty.Project.validation.impl.SubjectRules;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final EngagementRepository engagementRepository;
    private final LectureRepository lectureRepository;
    private final LectureScheduleRepository lectureScheduleRepository;
    private final SubjectRules subjectRules;

    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

    public Subject findById(Long id) throws FileNotFoundException {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()){

            return optionalSubject.get();
        } else {
            throw new FileNotFoundException("Subject with ID = " + id + " does not exist!");
        }
    }

    public Subject create(Subject subject){
        subjectRules.all(subject);
        return subjectRepository.save(subject);
    }

    public Subject edit(Subject subject) throws FileNotFoundException {
        findById(subject.getId());

        subjectRules.all(subject);
        return subjectRepository.save(subject);
    }

    public void delete(Long subjectId) throws FileNotFoundException {
        findById(subjectId);

        subjectRepository.deleteById(subjectId);
    }

    public Subject updateLecturePlan(Long subjectId, LecturePlan lecturePlan) throws FileNotFoundException {
        Subject subject = findById(subjectId);
        subject.createLecturePlan(lecturePlan.getPredavanja(), lecturePlan.getVezbe(), lecturePlan.getLaboratorija());
        return subjectRepository.save(subject);
    }

    public Subject generateBlankSchedule(Long subjectId, int year) throws FileNotFoundException {
        Subject subject = findById(subjectId);
        LecturePlan plan = subject.getLecturePlan();
        if (plan == null || !plan.isSet()) throw new IllegalArgumentException("Lecture plan isn't set for the subject " + subject.getName());

        List<Engagement> engagements = engagementRepository.findAllBySubjectAndYear(subject, year);
        if (engagements == null || engagements.isEmpty()) throw new IllegalArgumentException("There are no engagements for " + subject.getName() + " for year " + year);

        LectureSchedule finalSchedule = new LectureSchedule();
        finalSchedule.setYear(year);
        finalSchedule.setSubject(subject);

        List<Engagement> laboranti = new LinkedList<>();
        List<Engagement> vezbaci = new LinkedList<>();
        List<Engagement> predavaci = new LinkedList<>();

        for (Engagement e : engagements){
            System.err.println("Predavac " + e.getMember().getFirstname() + " je na " + e.getLectureForms().size() + " razlicih oblika nastave");
            for (LectureForm form : e.getLectureForms()){
                switch (form){
                    case Laboratorija -> laboranti.add(e);
                    case Predavanja -> predavaci.add(e);
                    case Vezbe -> vezbaci.add(e);
                }
            }
        }
        int fondLab = plan.getLaboratorija();
        int fondVez = plan.getVezbe();
        int fondPred = plan.getPredavanja();

        System.err.println("Fond lab: " + fondLab);
        System.err.println("Fond vez: " + fondVez);
        System.err.println("Fond pred: " + fondPred);
        System.err.println("broj laboranata: " + laboranti.size());
        System.err.println("broj vezbaca: " + vezbaci.size());
        System.err.println("broj predavaca: " + predavaci.size());

        Comparator<Engagement> comparator = new Comparator<Engagement>() {
            //Sortira tako da prvo dobiju predavanja ovi najmanje optereceni
            //nakon toga uzima u obzir rang na fakultetu, tako da visi rang znaci veca rasterecenost
            @Override
            public int compare(Engagement o1, Engagement o2) {
                int zauzetost = o2.getLectureForms().size()-o1.getLectureForms().size();
                if (zauzetost!=0) return zauzetost;
                int rang = Long.compare(o2.getMember().getAcademicTitle().getId(), o1.getMember().getAcademicTitle().getId());
                return rang;
            }
        };
        if (!predavaci.isEmpty()) predavaci.sort(comparator);
        if (!laboranti.isEmpty()) laboranti.sort(comparator);
        if (!vezbaci.isEmpty()) vezbaci.sort(comparator);

        List<Lecture> lectures = new ArrayList<>(fondLab + fondPred + fondVez);

        if (predavaci.isEmpty() && fondPred>0) throw new DataIntegrityViolationException("Fond za predavanja je " + fondPred + " a predavaci nisu dodeljeni");

        for (int preostalaPredavanja = fondPred; preostalaPredavanja > 0;){
            int predavanjaPoNastavniku = preostalaPredavanja / predavaci.size();
            Engagement predavac = predavaci.remove(0);
            System.err.println("Predavac " + predavac.getMember().getFirstname() + " je dobio " + predavanjaPoNastavniku + " predavanja");
            for (int i = 0; i < predavanjaPoNastavniku; i++){
                lectures.add(new Lecture(predavac, LectureForm.Predavanja, finalSchedule));
            }
            preostalaPredavanja-=predavanjaPoNastavniku;

        }

        if (vezbaci.isEmpty() && fondVez>0) throw new DataIntegrityViolationException("Fond za vezbe je " + fondVez + " a predavaci nisu dodeljeni");

        for (int preostaleVezbe = fondVez; preostaleVezbe > 0;){
            int vezbePoNastavniku = preostaleVezbe / vezbaci.size();
            Engagement vezbac = vezbaci.remove(0);
            System.err.println("Vezbac " + vezbac.getMember().getFirstname() + " je dobio " + vezbePoNastavniku + " vezbi");
            for (int i = 0; i < vezbePoNastavniku; i++){
                lectures.add(new Lecture(vezbac, LectureForm.Vezbe, finalSchedule));
            }
            preostaleVezbe-=vezbePoNastavniku;
        }

        if (laboranti.isEmpty() && fondLab>0) throw new DataIntegrityViolationException("Fond za laboratoriju je " + fondLab + " a predavaci nisu dodeljeni");

        for (int preostaleLab = fondLab; preostaleLab > 0;){
            int labPoNastavniku = preostaleLab / laboranti.size();
            Engagement laborant = laboranti.remove(0);
            System.err.println("Laborant " + laborant.getMember().getFirstname() + " je dobio " + labPoNastavniku + " laboratorijskih vezbi");
            for (int i = 0; i < labPoNastavniku; i++){
                lectures.add(new Lecture(laborant, LectureForm.Laboratorija, finalSchedule));
            }
            preostaleLab-=labPoNastavniku;
        }

        List<LectureSchedule> existingLectures = lectureScheduleRepository.findAllBySubjectIdAndYear(subjectId,year);
        lectureScheduleRepository.deleteAll(existingLectures);

        List<Lecture> persLectures = lectureRepository.saveAll(lectures);
        finalSchedule.setLectures(persLectures);
        subject.setSchedule(finalSchedule);

        return subjectRepository.save(subject);
    }
}
