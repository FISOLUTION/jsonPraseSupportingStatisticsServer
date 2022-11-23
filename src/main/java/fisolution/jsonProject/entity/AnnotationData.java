package fisolution.jsonProject.entity;

import fisolution.jsonProject.controller.requestdto.AnnotationDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ANNOTATIONDATA")
public class AnnotationData {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String annotationId;

    private String objectId;

    private String iou;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspectionId")
    private Inspection inspection;

    @Builder
    public AnnotationData(Long id, String annotationId, String objectId, String iou, Inspection inspection) {
        this.id = id;
        this.annotationId = annotationId;
        this.objectId = objectId;
        this.iou = iou;
        this.inspection = inspection;
    }

    public AnnotationData(AnnotationDTO annotationDTO, Inspection inspection) {
        this.annotationId = annotationDTO.getAnnotationId();
        this.objectId = annotationDTO.getObjectId();
        this.iou = annotationDTO.getIou();
        this.inspection = inspection;
    }
}