package fon.mas.novica.Faculty.Project.validation.impl;

import fon.mas.novica.Faculty.Project.entity.Member;
import fon.mas.novica.Faculty.Project.validation.AbstractRules;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class MemberRules extends AbstractRules<Member> {
    @Override
    public void all(Member member) {
        checkFields(member);
    }

    public void checkFields(Member member){
        if (member.getFirstname()==null) abort("First name cant be null");
        if (member.getFirstname().isBlank()) abort("First name cant be blank");
        if (member.getLastname()==null) abort("Last name cant be null");
        if (member.getLastname().isBlank()) abort("Last name cant be blank");
        if (member.getAcademicTitle()==null) abort("Academic title isnt set");
        if (member.getEducationTitle()==null) abort("Education title isnt set");
        if (member.getScientificField()==null) abort("Scientific field isnt set");
    }
}
