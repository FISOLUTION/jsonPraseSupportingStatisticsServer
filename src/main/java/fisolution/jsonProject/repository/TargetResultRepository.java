package fisolution.jsonProject.repository;

import fisolution.jsonProject.entity.TargetResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TargetResultRepository extends JpaRepository<TargetResults, Long> {
}
