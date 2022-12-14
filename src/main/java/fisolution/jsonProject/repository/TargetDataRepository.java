package fisolution.jsonProject.repository;

import fisolution.jsonProject.controller.responsedto.TargetDataResponseDTO;
import fisolution.jsonProject.entity.TargetData;
import fisolution.jsonProject.repository.dao.CountByObjectNameDAO;
import fisolution.jsonProject.repository.dao.OverviewDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TargetDataRepository extends JpaRepository<TargetData, Long> {

    @Query("select new fisolution.jsonProject.controller.responsedto.TargetDataResponseDTO(t, r) " +
            "from TargetData t " +
            "join t.result as r " +
            "where t.id = :id")
    TargetDataResponseDTO findByIdWithResult(@Param("id") Long id);

    @Query("select new fisolution.jsonProject.repository.dao." +
            "OverviewDAO(t.status, count(t.id), sum(t.annotationCnt)) " +
            "from TargetData t where t.dataSetName = :dataSetName " +
            "group by t.status")
    List<OverviewDAO> statisticOverviewQuery(@Param("dataSetName") String dataSetName);

//    @Query(value = "select sum(r.status) over (partition by r.g011 group by r.status) s from TargetResults r", nativeQuery = true)
//    Long statisticInspection();

    @Query("select new fisolution.jsonProject.repository.dao." +
            "CountByObjectNameDAO(cd.id, cd.categoryName, cd.superCategoryName, cd.in_out, count(cd.id), sum(t.annotationCnt)) " +
            "from CategoryData cd " +
            "left join cd.categoryList as c " +
            "left join c.targetData as t on t.dataSetName = :dataSetName " +
            "group by cd.id")
    List<CountByObjectNameDAO> statisticPerObject(@Param("dataSetName") String dataSetName);

}
