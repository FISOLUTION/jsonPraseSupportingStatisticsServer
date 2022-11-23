package fisolution.jsonProject.controller.requestdto;

import fisolution.jsonProject.entity.enumtype.DataStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchInspectionRequestDTO {

    private String dataSetName;
    private String imageFileName;

}
