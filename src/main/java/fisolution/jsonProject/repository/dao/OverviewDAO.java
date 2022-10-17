package fisolution.jsonProject.repository.dao;

import fisolution.jsonProject.entity.enumtype.DataStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OverviewDAO {
    private DataStatus status;
    private Long totalCnt;
    private Long totalAnnotationCnt;
}
