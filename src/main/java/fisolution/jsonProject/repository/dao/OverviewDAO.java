package fisolution.jsonProject.repository.dao;

import fisolution.jsonProject.entity.DataStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class OverviewDAO {
    private DataStatus status;
    private Long totalCnt;
    private Long totalAnnotationCnt;
}
