package fisolution.jsonProject.controller.requestdto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AnnotationDTO {

    private String annotationId;
    private String objectId;
    private String iou;

    @Builder
    public AnnotationDTO(String annotationId, String objectId, String iou) {
        this.annotationId = annotationId;
        this.objectId = objectId;
        this.iou = iou;
    }
}
