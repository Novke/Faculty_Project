package fon.mas.novica.Faculty.Project.validation.impl;

import fon.mas.novica.Faculty.Project.entity.Engagement;
import fon.mas.novica.Faculty.Project.validation.AbstractRules;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class EngagementRules extends AbstractRules<Engagement> {

    private final int maxYear = 2099;
    private final int minYear = 2000;
    @Override
    public void all(Engagement engagement) {
        checkFields(engagement);
        checkRelations(engagement);
    }

    public void checkRelations(Engagement engagement) {
        if (engagement.getSubject().getDepartment().getId()!=
            engagement.getMember().getDepartment().getId()) abort("Subject and member aren't from same department");
    }

    public void checkFields(Engagement engagement) {
        if (engagement.getMember()==null) abort("Member isnt set for engagement");
        if (engagement.getSubject()==null) abort("Subject isnt set for engagement");
        if (engagement.getLectureForms()==null || engagement.getLectureForms().isEmpty()) abort("Lecture forms arent set for engagement");
        if (engagement.getYear()<minYear || engagement.getYear()>maxYear) abort("Year must be between " + minYear + " and " + maxYear);
    }
}
