package fon.mas.novica.Faculty.Project.validation.impl;

import fon.mas.novica.Faculty.Project.entity.ManagerMandate;
import fon.mas.novica.Faculty.Project.validation.AbstractRules;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class ManagerMandateRules extends AbstractRules<ManagerMandate> {
    @Override
    public void all(ManagerMandate mandate) {
        checkFields(mandate);
        checkRelations(mandate);
    }

    private void checkRelations(ManagerMandate mandate) {
        if (mandate.getDepartment().getId()!=
                mandate.getManager().getDepartment().getId())
            abort("Manager must be chosen within the same department");
    }

    private void checkFields(ManagerMandate mandate) {
        if (mandate.getManager()==null) abort("Manager isnt set for the mandate");
        if (mandate.getDepartment()==null) abort("Department isnt set for the manager mandate");
    }
}
