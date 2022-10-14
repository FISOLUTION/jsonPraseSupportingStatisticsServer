package fisolution.jsonProject.repository;

import fisolution.jsonProject.entity.TargetResults;
import fisolution.jsonProject.repository.dao.CountDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountQueryLegacy extends JpaRepository<TargetResults, Long> {

    @Query("select new fisolution.jsonProject.repository.dao.CountDAO('G011', r.g011, count(r.id)) from TargetResults r join r.targetData as t where t.dataSetName = :dataSetName group by r.g011")
    List<CountDAO> countG011(@Param("dataSetName") String dataSetName);

    @Query("select new fisolution.jsonProject.repository.dao.CountDAO('G022', r.g022, count(r.id)) from TargetResults r join r.targetData as t where t.dataSetName = :dataSetName group by r.g022")
    List<CountDAO> countG022(@Param("dataSetName") String dataSetName);

    @Query("select new fisolution.jsonProject.repository.dao.CountDAO('G031', r.g031, count(r.id)) from TargetResults r join r.targetData as t where t.dataSetName = :dataSetName group by r.g031")
    List<CountDAO> countG031(@Param("dataSetName") String dataSetName);

    @Query("select new fisolution.jsonProject.repository.dao.CountDAO('G041', r.g041, count(r.id)) from TargetResults r join r.targetData as t where t.dataSetName = :dataSetName group by r.g041")
    List<CountDAO> countG041(@Param("dataSetName") String dataSetName);

    @Query("select new fisolution.jsonProject.repository.dao.CountDAO('G051', r.g051, count(r.id)) from TargetResults r join r.targetData as t where t.dataSetName = :dataSetName group by r.g051")
    List<CountDAO> countG051(@Param("dataSetName") String dataSetName);

    @Query("select new fisolution.jsonProject.repository.dao.CountDAO('G052', r.g052, count(r.id)) from TargetResults r join r.targetData as t where t.dataSetName = :dataSetName group by r.g052")
    List<CountDAO> countG052(@Param("dataSetName") String dataSetName);

    @Query("select new fisolution.jsonProject.repository.dao.CountDAO('G053', r.g053, count(r.id)) from TargetResults r join r.targetData as t where t.dataSetName = :dataSetName group by r.g053")
    List<CountDAO> countG053(@Param("dataSetName") String dataSetName);

    @Query("select new fisolution.jsonProject.repository.dao.CountDAO('G061', r.g061, count(r.id)) from TargetResults r join r.targetData as t where t.dataSetName = :dataSetName group by r.g061")
    List<CountDAO> countG061(@Param("dataSetName") String dataSetName);

    @Query("select new fisolution.jsonProject.repository.dao.CountDAO('G071', r.g071, count(r.id)) from TargetResults r join r.targetData as t where t.dataSetName = :dataSetName group by r.g071")
    List<CountDAO> countG071(@Param("dataSetName") String dataSetName);

    @Query("select new fisolution.jsonProject.repository.dao.CountDAO('G081', r.g081, count(r.id)) from TargetResults r join r.targetData as t where t.dataSetName = :dataSetName group by r.g081")
    List<CountDAO> countG081(@Param("dataSetName") String dataSetName);

    @Query("select new fisolution.jsonProject.repository.dao.CountDAO('G091', r.g091, count(r.id)) from TargetResults r join r.targetData as t where t.dataSetName = :dataSetName group by r.g091")
    List<CountDAO> countG091(@Param("dataSetName") String dataSetName);

    @Query("select new fisolution.jsonProject.repository.dao.CountDAO('G101', r.g101, count(r.id)) from TargetResults r join r.targetData as t where t.dataSetName = :dataSetName group by r.g101")
    List<CountDAO> countG101(@Param("dataSetName") String dataSetName);

    @Query("select new fisolution.jsonProject.repository.dao.CountDAO('M011', r.m011, count(r.id)) from TargetResults r join r.targetData as t where t.dataSetName = :dataSetName group by r.m011")
    List<CountDAO> countM011(@Param("dataSetName") String dataSetName);

    @Query("select new fisolution.jsonProject.repository.dao.CountDAO('M021', r.m021, count(r.id)) from TargetResults r join r.targetData as t where t.dataSetName = :dataSetName group by r.m021")
    List<CountDAO> countM021(@Param("dataSetName") String dataSetName);

    @Query("select new fisolution.jsonProject.repository.dao.CountDAO('M031', r.m031, count(r.id)) from TargetResults r join r.targetData as t where t.dataSetName = :dataSetName group by r.m031")
    List<CountDAO> countM031(@Param("dataSetName") String dataSetName);
}
