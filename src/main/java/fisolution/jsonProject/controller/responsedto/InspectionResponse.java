package fisolution.jsonProject.controller.responsedto;

import com.querydsl.core.annotations.QueryProjection;
import fisolution.jsonProject.entity.Inspection;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class InspectionResponse {
    private Long inspectionId;
    private String imageFileName;
    private String dataSetName;
    private Integer annotationCnt;
    private List<AnnotationResponse> annotationList;

    public InspectionResponse(Inspection inspection) {
        this.inspectionId = inspection.getId();
        this.imageFileName = inspection.getImageFileName();
        this.dataSetName = inspection.getDataSetName();
        this.annotationCnt = inspection.getAnnotationCnt();
        this.annotationList = inspection.getAnnotationList()
                .stream().map(AnnotationResponse::new)
                .collect(Collectors.toList());
    }

    @QueryProjection
    public InspectionResponse(Long inspectionId, String imageFileName, String dataSetName, Integer annotationCnt, List<AnnotationResponse> annotationList) {
        this.inspectionId = inspectionId;
        this.imageFileName = imageFileName;
        this.dataSetName = dataSetName;
        this.annotationCnt = annotationCnt;
        this.annotationList = annotationList;
    }
}
