package fisolution.jsonProject.controller.responsedto;

import fisolution.jsonProject.controller.requestdto.TargetResultDTO;
import fisolution.jsonProject.entity.enumtype.DataStatus;
import fisolution.jsonProject.entity.TargetData;
import fisolution.jsonProject.entity.TargetResults;
import lombok.Data;

@Data
public class TargetDataResponseDTO {

    private Long id;
    private String fileName;
    private String imageId;
    private String dataSetName;
    private Integer annotationCnt;
    private String objectName;
    // 이 부분은 논의 필요 server 에서 할 것인가 python 쪽에서 할 것인가
    private DataStatus status;

    private TargetResultDTO targetResultDTO;

    public TargetDataResponseDTO(TargetData entity, TargetResults sub){
        id = entity.getId();
        fileName = entity.getFileName();
        imageId = entity.getImageId();
        dataSetName = entity.getDataSetName();
        annotationCnt = entity.getAnnotationCnt();
        objectName = entity.getObjectName();
        status = entity.getStatus();
        targetResultDTO = new TargetResultDTO(sub);
    }
}
