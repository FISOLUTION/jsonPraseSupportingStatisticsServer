package fisolution.jsonProject.repository;

import fisolution.jsonProject.controller.responsedto.TargetDataResponseDTO;
import fisolution.jsonProject.entity.TargetData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TargetDataRepository extends JpaRepository<TargetData, Long> {

    @Query("select new fisolution.jsonProject.controller.responsedto.TargetDataResponseDTO(t, r) " +
            "from TargetData t " +
            "join t.result as r " +
            "where t.id = :id")
    TargetDataResponseDTO findByIdWithResult(@Param("id") Long id);
}
