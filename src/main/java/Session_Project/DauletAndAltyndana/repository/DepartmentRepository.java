package Session_Project.DauletAndAltyndana.repository;

import Session_Project.DauletAndAltyndana.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
