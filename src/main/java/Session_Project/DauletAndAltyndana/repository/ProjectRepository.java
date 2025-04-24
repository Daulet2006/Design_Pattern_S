package Session_Project.DauletAndAltyndana.repository;

import Session_Project.DauletAndAltyndana.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}