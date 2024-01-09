package fon.mas.novica.Faculty.Project.service;

import fon.mas.novica.Faculty.Project.entity.Department;
import fon.mas.novica.Faculty.Project.entity.ManagerMandate;
import fon.mas.novica.Faculty.Project.entity.Member;
import fon.mas.novica.Faculty.Project.entity.SecretaryMandate;
import fon.mas.novica.Faculty.Project.repository.DepartmentRepository;
import fon.mas.novica.Faculty.Project.repository.ManagerMandateRepository;
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
        return departmentRepository.save(department);
    }

    public Department edit(Department department) throws FileNotFoundException {
        findById(department.getId()); //baca izuzetak ako ne postoji

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

        department.getManagerHistory().add(newMandate);
        departmentRepository.save(department); //cuva preko cascade
    }

    public void assignNewSecretary(Department department, Member member){
        department.setSecretary(member);

        SecretaryMandate newMandate = new SecretaryMandate();
        newMandate.setDepartment(department);
        newMandate.setSecretary(member);
        newMandate.setStartDate(LocalDate.now());

        department.getSecretaryHistory().add(newMandate);
        departmentRepository.save(department); //cuva preko cascade
    }
}
