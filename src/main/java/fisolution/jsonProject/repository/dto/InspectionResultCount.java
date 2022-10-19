package fisolution.jsonProject.repository.dto;

import fisolution.jsonProject.entity.enumtype.DataStatus;
import fisolution.jsonProject.entity.enumtype.InspectionType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InspectionResultCount {

    private DataStatus dataStatus;
    private InspectionType inspectionType;
    private Long cnt;
}
