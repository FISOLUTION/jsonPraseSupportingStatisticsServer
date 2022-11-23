package fisolution.jsonProject.repository;

import fisolution.jsonProject.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InspectionRepository extends JpaRepository<Inspection, Long> {

    @Query("select distinct i from Inspection i " +
            "join fetch i.annotationList " +
            "where i.id = :inspectionId")
    Optional<Inspection> findByIdWithAnnotation(@Param("inspectionId") Long inspectionId);
}
