package fisolution.jsonProject.repository;

import fisolution.jsonProject.entity.Category;
import fisolution.jsonProject.entity.InspectionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BulkInsertRepository {

    private final JdbcTemplate jdbcTemplate;

    public void bulkInsertCategory(List<Category> categories){
        jdbcTemplate.batchUpdate(
                "insert into Category (TARGETDATAID, CATEGORYDATAID) VALUES (?, ?)",
                categories,
                100,
                (PreparedStatement ps, Category category) -> {
                    ps.setLong(1, category.getTargetData().getId());
                    ps.setLong(2, category.getCategoryData().getId());
                });
    }

    public void bulkInsertInspectionResult(List<InspectionResult> inspectionResults){
        jdbcTemplate.batchUpdate(
                "insert into InspectionResult (DATASTATUS, INSPECTIONTYPE, TARGETDATA_ID) VALUES (?, ?, ?)",
                inspectionResults,
                100,
                (PreparedStatement ps, InspectionResult inspectionResult) -> {
                    ps.setString(1, inspectionResult.getDataStatus().toString());
                    ps.setString(2, inspectionResult.getInspectionType().toString());
                    ps.setLong(3, inspectionResult.getTargetData().getId());
                });
    }
}
