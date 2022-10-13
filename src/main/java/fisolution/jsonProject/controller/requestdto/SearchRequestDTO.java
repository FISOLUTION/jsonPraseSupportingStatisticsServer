package fisolution.jsonProject.controller.requestdto;

import fisolution.jsonProject.entity.DataStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchRequestDTO {

    private String dataSetName;
    private DataStatus dataStatus;
    private String objectName;
    private String imageId;

}
