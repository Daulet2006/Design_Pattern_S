package Session_Project.DauletAndAltyndana.repository;

import Session_Project.DauletAndAltyndana.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContainingIgnoreCase(String name);
    List<Employee> findByPositionContainingIgnoreCase(String position);
}
