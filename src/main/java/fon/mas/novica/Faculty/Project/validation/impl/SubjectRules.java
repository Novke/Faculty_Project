package fon.mas.novica.Faculty.Project.validation.impl;

import fon.mas.novica.Faculty.Project.entity.Subject;
import fon.mas.novica.Faculty.Project.validation.AbstractRules;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class SubjectRules extends AbstractRules<Subject> {
    @Override
    public void all(Subject subject) {
        checkFields(subject);
    }

    public void checkFields(Subject subject){
        if (subject.getName()==null) abort("Subject name cant be null");
        if (subject.getName().isBlank()) abort("Subject name cant be blank");
        if (subject.getEspb()<=0) abort("ESPB must be > 0");
        if (subject.getDepartment()==null) abort("Department isnt set for the subject");
    }
}
