package fon.mas.novica.Faculty.Project.serialization.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fon.mas.novica.Faculty.Project.entity.*;

import java.io.IOException;

public class DepartmentSerializer extends JsonSerializer<Department> {

    public void serialize(Department department, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", department.getId());
        jsonGenerator.writeStringField("name", department.getName());
        jsonGenerator.writeStringField("shortName", department.getShortName());
        if (department.getManager()!=null){
            Member manager = department.getManager();
            jsonGenerator.writeObjectFieldStart("manager");
            jsonGenerator.writeNumberField("id", manager.getId());
            jsonGenerator.writeStringField("firstname", manager.getFirstname());
            jsonGenerator.writeStringField("lastname", manager.getLastname());
            jsonGenerator.writeEndObject();
        }
        if (department.getSecretary()!=null){
            Member secretary = department.getSecretary();
            jsonGenerator.writeObjectFieldStart("secretary");
            jsonGenerator.writeNumberField("id", secretary.getId());
            jsonGenerator.writeStringField("firstname", secretary.getFirstname());
            jsonGenerator.writeStringField("lastname", secretary.getLastname());
            jsonGenerator.writeEndObject();
        }
        if (department.getSubjects()!=null){
            jsonGenerator.writeArrayFieldStart("subjects");

            for (Subject subject : department.getSubjects()){
                jsonGenerator.writeStartObject();

                jsonGenerator.writeNumberField("id", subject.getId());
                jsonGenerator.writeStringField("name", subject.getName());
                jsonGenerator.writeNumberField("espb", subject.getEspb());

                jsonGenerator.writeEndObject();
            }

            jsonGenerator.writeEndArray();
        }

        if (department.getManagerHistory()!=null){
            jsonGenerator.writeArrayFieldStart("managerHistory");

            for (ManagerMandate mandate : department.getManagerHistory()){
                jsonGenerator.writeStartObject();

                jsonGenerator.writeNumberField("id", mandate.getId());
                jsonGenerator.writeStringField("firstname", mandate.getManager().getFirstname());
                jsonGenerator.writeStringField("lastname", mandate.getManager().getLastname());
                jsonGenerator.writeStringField("startDate", mandate.getStartDate().toString());

                jsonGenerator.writeEndObject();
            }

            jsonGenerator.writeEndArray();
        }
        if (department.getSecretaryHistory()!=null){
            jsonGenerator.writeArrayFieldStart("secretaryHistory");

            for (SecretaryMandate mandate : department.getSecretaryHistory()){
                jsonGenerator.writeStartObject();

                jsonGenerator.writeNumberField("id", mandate.getId());
                jsonGenerator.writeStringField("firstname", mandate.getSecretary().getFirstname());
                jsonGenerator.writeStringField("lastname", mandate.getSecretary().getLastname());
                jsonGenerator.writeStringField("startDate", mandate.getStartDate().toString());

                jsonGenerator.writeEndObject();
            }

            jsonGenerator.writeEndArray();
        }


        jsonGenerator.writeEndObject();
    }
}
