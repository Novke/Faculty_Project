package fon.mas.novica.Faculty.Project.validation.impl;

import fon.mas.novica.Faculty.Project.entity.Lecture;
import fon.mas.novica.Faculty.Project.validation.AbstractRules;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LectureRules extends AbstractRules<Lecture> {
    @Override
    public void all(Lecture lecture) {
        checkFields(lecture);
        checkRelations(lecture);
    }

    private void checkRelations(Lecture lecture) {
        if (!lecture.getEngagement().getLectureForms().contains(lecture.getFormat()))
            abort("Selected format '" + lecture.getFormat().name() + "' isnt suitable for engagement. (Member = " + lecture.getEngagement().getMember().getFirstname() + " )");
    }

    private void checkFields(Lecture lecture) {
        if (lecture.getFormat()==null) abort("Format isnt set for lecture");
        if (lecture.getEngagement()==null) abort("Engagement isnt set for lecture");
    }
}
