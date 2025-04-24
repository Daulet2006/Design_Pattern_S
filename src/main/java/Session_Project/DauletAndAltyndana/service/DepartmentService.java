package Session_Project.DauletAndAltyndana.service;

import Session_Project.DauletAndAltyndana.model.Department;
import Session_Project.DauletAndAltyndana.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
        department.setName(updatedDepartment.getName());
        department.setEmployees(updatedDepartment.getEmployees());
        department.setAddress(updatedDepartment.getAddress());
        return departmentRepository.save(department);
    }
}