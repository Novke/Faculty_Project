package fon.mas.novica.Faculty.Project.serialization.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fon.mas.novica.Faculty.Project.entity.*;

import java.io.IOException;
import java.util.List;

public class SubjectSerializer extends JsonSerializer<Subject> {
    @Override
    public void serialize(Subject subject, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", subject.getId());
        jsonGenerator.writeStringField("name", subject.getName());
        jsonGenerator.writeNumberField("espb", subject.getEspb());

        Department department = subject.getDepartment();
        jsonGenerator.writeObjectFieldStart("department");
        jsonGenerator.writeNumberField("id", department.getId());
        jsonGenerator.writeStringField("name", department.getName());
        jsonGenerator.writeEndObject();

        LecturePlan plan = subject.getLecturePlan();
        if (plan!=null) {
            jsonGenerator.writeObjectFieldStart("lecturePlan");
            jsonGenerator.writeNumberField("predavanja", plan.getPredavanja());
            jsonGenerator.writeNumberField("vezbe", plan.getVezbe());
            jsonGenerator.writeNumberField("laboratorija", plan.getLaboratorija());
            jsonGenerator.writeEndObject();
        }

        List<Engagement> lecturers = subject.getLecturers();
        jsonGenerator.writeArrayFieldStart("lecturers");
        if (lecturers!=null) {
            for (Engagement l : lecturers) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("predavac", l.getMember().getFirstname() + " " + l.getMember().getLastname());
                StringBuilder sb = new StringBuilder();
                for (LectureForm form : l.getLectureForms()) {
                    sb.append(form.name()).append(", ");
                }
                sb.delete(sb.length() - 2, sb.length() - 1);
                jsonGenerator.writeStringField("formati", sb.toString());
                jsonGenerator.writeEndObject();
            }
        }
        jsonGenerator.writeEndArray();

        LectureSchedule schedule = subject.getLatestSchedule();
        if (schedule!=null){
            jsonGenerator.writeObjectFieldStart("schedule");
            jsonGenerator.writeNumberField("id", schedule.getId());
            jsonGenerator.writeNumberField("year", schedule.getYear());
            List<Lecture> lectures = schedule.getLectures();
            jsonGenerator.writeArrayFieldStart("lectures");
            if (lectures!=null){
                for (Lecture l : lectures){
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("format", l.getFormat().name());
                    if (l.getDateTime()!=null) jsonGenerator.writeStringField("dateTime", l.getDateTime().toString());
                    jsonGenerator.writeStringField("title", l.getTitle() == null ? "" : l.getTitle());
                    jsonGenerator.writeStringField("predavac", l.getEngagement().getMember().getFirstname());
                    jsonGenerator.writeEndObject();
                }
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndObject();
    }
}
