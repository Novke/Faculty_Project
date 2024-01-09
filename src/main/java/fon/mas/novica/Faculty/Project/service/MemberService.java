package fon.mas.novica.Faculty.Project.service;

import fon.mas.novica.Faculty.Project.entity.AcademicTitleHistory;
import fon.mas.novica.Faculty.Project.entity.Member;
import fon.mas.novica.Faculty.Project.repository.AcademicTitleHistoryRepository;
import fon.mas.novica.Faculty.Project.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AcademicTitleHistoryRepository academicTitleHistoryRepository;

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
            member.setAcademicTitles(List.of(academicTitleHistoryRepository.save(titleHistory)));
        }

        return memberRepository.save(member);
    }

    public Member edit(Member member) throws FileNotFoundException {
        findById(member.getId());

        AcademicTitleHistory lastTitle = academicTitleHistoryRepository.findByMemberSortByStartDateDesc(member).get(0);
        lastTitle.setScientificField(member.getScientificField());
        lastTitle.setAcademicTitle(member.getAcademicTitle());

        return memberRepository.save(member);
    }

    public void delete(Long memberId) throws FileNotFoundException {
        findById(memberId);

        memberRepository.deleteById(memberId);
    }

}
