package fisolution.jsonProject.controller.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InspectResultDTO {

    private String inspectName;
    private double passRate;
    private long errorCnt;

}
