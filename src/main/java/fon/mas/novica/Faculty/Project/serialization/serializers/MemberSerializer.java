package fon.mas.novica.Faculty.Project.serialization.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fon.mas.novica.Faculty.Project.entity.LectureForm;
import fon.mas.novica.Faculty.Project.entity.Member;

import java.io.IOException;

public class MemberSerializer extends JsonSerializer<Member>{

    @Override
    public void serialize(Member member, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", member.getId());
        jsonGenerator.writeStringField("firstname", member.getFirstname());
        jsonGenerator.writeStringField("lastname", member.getLastname());
        jsonGenerator.writeStringField("academicTitle", member.getAcademicTitle().getName());
        jsonGenerator.writeStringField("scientificField", member.getScientificField().getName());
        jsonGenerator.writeStringField("educationTitle", member.getEducationTitle().getName());

        jsonGenerator.writeObjectFieldStart("department");
        jsonGenerator.writeNumberField("id", member.getDepartment().getId());
        jsonGenerator.writeStringField("name", member.getDepartment().getName());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeArrayFieldStart("engagements");
        if (member.getEngagements() != null){
            member.getEngagements().forEach(engagement -> {
                try {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeNumberField("id", engagement.getId());
                    jsonGenerator.writeStringField("subject", engagement.getSubject().getName());
                    jsonGenerator.writeNumberField("year", engagement.getYear());
                    jsonGenerator.writeArrayFieldStart("lectureForms");
                    if (engagement.getLectureForms() != null){
                        for (LectureForm form : engagement.getLectureForms()){
                            jsonGenerator.writeString(form.name());
                        }
                    }
                    jsonGenerator.writeEndArray();
                    jsonGenerator.writeEndObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        jsonGenerator.writeEndArray();


        jsonGenerator.writeArrayFieldStart("academicTitles");
        if (member.getAcademicTitles() != null){
            member.getAcademicTitles().forEach(title -> {
                try {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeNumberField("id", title.getId());
                    jsonGenerator.writeStringField("academicTitle", title.getAcademicTitle().getName());
                    jsonGenerator.writeStringField("scientificField", title.getScientificField().getName());
                    jsonGenerator.writeStringField("startDate", title.getStartDate().toString());
                    jsonGenerator.writeStringField("endDate", title.getEndDate() != null ? title.getEndDate().toString() : "");
                    jsonGenerator.writeEndObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
