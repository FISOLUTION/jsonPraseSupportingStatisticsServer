package fisolution.jsonProject.repository;

import fisolution.jsonProject.controller.responsedto.InspectResultDTO;
import fisolution.jsonProject.entity.InspectionResult;
import fisolution.jsonProject.repository.dao.InspectionResultDAO;
import fisolution.jsonProject.repository.dto.InspectionResultCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InspectionResultRepository extends JpaRepository<InspectionResult, Long> {

    @Query("select new fisolution.jsonProject.repository.dto.InspectionResultCount(i.dataStatus, i.inspectionType, count(i)) " +
            "from InspectionResult i " +
            "join i.targetData as t on t.dataSetName = :dataSetName " +
            "group by i.inspectionType, i.dataStatus")
    List<InspectionResultCount> inspectResultPerDataSetName(String dataSetName);

}
