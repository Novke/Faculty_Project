package fon.mas.novica.Faculty.Project.validation.impl;

import fon.mas.novica.Faculty.Project.entity.SecretaryMandate;
import fon.mas.novica.Faculty.Project.validation.AbstractRules;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class SecretaryMandateRules extends AbstractRules<SecretaryMandate> {
    @Override
    public void all(SecretaryMandate mandate) {
        checkFields(mandate);
        checkRelations(mandate);
    }

    public void checkRelations(SecretaryMandate mandate) {
        if (mandate.getDepartment().getId()!=
            mandate.getSecretary().getDepartment().getId())
            abort("Secretary must be chosen within the same department");
    }

    public void checkFields(SecretaryMandate mandate) {
        if (mandate.getSecretary()==null) abort("Secretary isnt set for the mandate");
        if (mandate.getDepartment()==null) abort("Department isnt set for the secretary mandate");
    }
}
