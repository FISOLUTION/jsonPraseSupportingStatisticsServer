package fisolution.jsonProject.controller.requestdto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class InspectionDTO {

    private String imageFileName;
    private String dataSetName;
    private List<AnnotationDTO> annotationList;

    @Builder
    public InspectionDTO(String imageFileName, String dataSetName, List<AnnotationDTO> annotationList) {
        this.imageFileName = imageFileName;
        this.dataSetName = dataSetName;
        this.annotationList = annotationList;
    }
}
