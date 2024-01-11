package fon.mas.novica.Faculty.Project.validation.impl;

import fon.mas.novica.Faculty.Project.entity.Department;
import fon.mas.novica.Faculty.Project.validation.AbstractRules;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class DepartmentRules extends AbstractRules<Department> {
    @Override
    public void all(Department department) {
        checkFields(department);
    }

    public void checkFields(Department department){
        if (department.getName()==null) abort("Name cant be null!");
        if (department.getName().isBlank()) abort("Name cant be blank!");
        if (department.getShortName()==null) abort("Short ame cant be null!");
        if (department.getShortName().isBlank()) abort("Short name cant be blank!");
    }
}
