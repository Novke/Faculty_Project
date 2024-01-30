package fon.mas.novica.Faculty.Project.service;

import fon.mas.novica.Faculty.Project.entity.Department;
import fon.mas.novica.Faculty.Project.entity.ManagerMandate;
import fon.mas.novica.Faculty.Project.entity.Member;
import fon.mas.novica.Faculty.Project.entity.SecretaryMandate;
import fon.mas.novica.Faculty.Project.repository.DepartmentRepository;
import fon.mas.novica.Faculty.Project.repository.ManagerMandateRepository;
import fon.mas.novica.Faculty.Project.repository.SecretaryMandateRepository;
import fon.mas.novica.Faculty.Project.validation.impl.DepartmentRules;
import fon.mas.novica.Faculty.Project.validation.impl.ManagerMandateRules;
import fon.mas.novica.Faculty.Project.validation.impl.SecretaryMandateRules;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentRules departmentRules;
    private final ManagerMandateRepository managerMandateRepository;
    private final SecretaryMandateRepository secretaryMandateRepository;
    private final SecretaryMandateRules secretaryMandateRules;
    private final ManagerMandateRules managerMandateRules;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
    public Department findById(Long id) throws FileNotFoundException {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()){
            return optionalDepartment.get();
        } else {
            throw new FileNotFoundException("Department with ID = " + id + " does not exist!");
        }
    }

    public Department create(Department department){
        departmentRules.all(department);
        return departmentRepository.save(department);
    }

    public Department edit(Department department) throws FileNotFoundException {
        findById(department.getId()); //baca izuzetak ako ne postoji
        departmentRules.all(department);

        return departmentRepository.save(department);
    }

    public void delete(Long departmentId) throws FileNotFoundException {
        findById(departmentId);

        departmentRepository.deleteById(departmentId);
    }

    public void assignNewManager(Department department, Member member) {
        department.setManager(member);

        ManagerMandate newMandate = new ManagerMandate();
        newMandate.setDepartment(department);
        newMandate.setManager(member);
        newMandate.setStartDate(LocalDate.now());

        managerMandateRules.all(newMandate);

//        department.getManagerHistory().add(newMandate);
//        departmentRepository.save(department); //cuva preko cascade

        managerMandateRepository.save(newMandate);
    }

    public void assignNewSecretary(Department department, Member member){
        department.setSecretary(member);

        SecretaryMandate newMandate = new SecretaryMandate();
        newMandate.setDepartment(department);
        newMandate.setSecretary(member);
        newMandate.setStartDate(LocalDate.now());

        secretaryMandateRules.all(newMandate);

//        department.getSecretaryHistory().add(newMandate);
//        departmentRepository.save(department); //cuva preko cascade

        secretaryMandateRepository.save(newMandate);
    }

    public void editManagerMandate(Department department, ManagerMandate mandate) throws FileNotFoundException {
        ManagerMandate oldMandate = managerMandateRepository.findById(mandate.getId()).orElseThrow(() -> new FileNotFoundException("Mandate with ID = " + mandate.getId() + " does not exist!"));
        managerMandateRules.all(mandate);
        managerMandateRepository.save(mandate);
    }

    public void editSecretaryMandate(Department department, SecretaryMandate mandate) throws FileNotFoundException {
        SecretaryMandate oldMandate = secretaryMandateRepository.findById(mandate.getId()).orElseThrow(() -> new FileNotFoundException("Mandate with ID = " + mandate.getId() + " does not exist!"));
        secretaryMandateRules.all(mandate);
        secretaryMandateRepository.save(mandate);
    }

    public void deleteManagerMandate(Department department, ManagerMandate mandate) {
        department.getManagerHistory().remove(mandate);
        departmentRepository.save(department);
    }

    public void deleteSecretaryMandate(Department department, SecretaryMandate mandate) {
        department.getSecretaryHistory().remove(mandate);
        departmentRepository.save(department);
    }
}
