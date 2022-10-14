package fisolution.jsonProject.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OverviewDTO {

    private long jsonCnt;
    private long annotationCnt;
    private double annotationCntPerJson;
    private double passRate;
    private double errorRate;
    private long errorCnt;

}
