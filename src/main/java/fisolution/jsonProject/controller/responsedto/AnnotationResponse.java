package fisolution.jsonProject.controller.responsedto;

import fisolution.jsonProject.entity.AnnotationData;
import lombok.Data;

@Data
public class AnnotationResponse {

    private Long id;
    private String annotationId;
    private String objectId;
    private String iou;

    public AnnotationResponse(AnnotationData annotationData) {
        this.id = annotationData.getId();
        this.annotationId = annotationData.getAnnotationId();
        this.objectId = annotationData.getObjectId();
        this.iou = annotationData.getIou();
    }
}
