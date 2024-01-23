package fon.mas.novica.Faculty.Project.service;

import fon.mas.novica.Faculty.Project.entity.AcademicTitle;
import fon.mas.novica.Faculty.Project.entity.AcademicTitleHistory;
import fon.mas.novica.Faculty.Project.entity.Member;
import fon.mas.novica.Faculty.Project.entity.ScientificField;
import fon.mas.novica.Faculty.Project.repository.AcademicTitleHistoryRepository;
import fon.mas.novica.Faculty.Project.repository.MemberRepository;
import fon.mas.novica.Faculty.Project.validation.impl.MemberRules;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AcademicTitleHistoryRepository academicTitleHistoryRepository;
    private final MemberRules memberRules;

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member findById(Long id) throws FileNotFoundException {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()){
            return optionalMember.get();
        } else {
            throw new FileNotFoundException("Member with ID = " + id + " does not exist!");
        }
    }

    public Member create(Member member){

        if (member.getAcademicTitles() == null || member.getAcademicTitles().isEmpty()){
            AcademicTitleHistory titleHistory = new AcademicTitleHistory(member, member.getAcademicTitle(), member.getScientificField());
//            member.setAcademicTitles(List.of(academicTitleHistoryRepository.save(titleHistory)));
            member.setAcademicTitles(List.of(titleHistory));
        }
        memberRules.all(member);

        return memberRepository.save(member);
    }

    public Member edit(Member member) throws FileNotFoundException {
        findById(member.getId());

        AcademicTitleHistory lastTitle = academicTitleHistoryRepository.findByMemberOrderByStartDateDescIdDesc(member).get(0);
        lastTitle.setScientificField(member.getScientificField());
        lastTitle.setAcademicTitle(member.getAcademicTitle());

        memberRules.all(member);

        return memberRepository.save(member);
    }

    public void delete(Long memberId) throws FileNotFoundException {
        findById(memberId);

        memberRepository.deleteById(memberId);
    }

    public Member promoteAcademicTitle(Member member, ScientificField newField, AcademicTitle newTitle) throws FileNotFoundException {
        Member vracenMember = findById(member.getId());

        if (vracenMember.getAcademicTitle().getId() == newTitle.getId()) throw new IllegalArgumentException("Can't promote member to same academic title");

        List<AcademicTitleHistory> titles = academicTitleHistoryRepository.findByMemberOrderByStartDateDescIdDesc(vracenMember);
        if (titles!=null && !titles.isEmpty()) {
            AcademicTitleHistory lastTitle = titles.get(0);
            lastTitle.setEndDate(LocalDate.now());
        }

//        if (newField == null) newField = vracenMember.getScientificField(); //NEMA POTREBE SVAKAKO JE newField IZVUCEN IZ BAZE

        AcademicTitleHistory newTitleHistory = new AcademicTitleHistory(vracenMember, newTitle, newField);
        vracenMember.setAcademicTitle(newTitle);
        vracenMember.setScientificField(newField);
        vracenMember.getAcademicTitles().add(newTitleHistory); //Cuva se preko cascade-a

        return memberRepository.save(vracenMember);
    }

}
