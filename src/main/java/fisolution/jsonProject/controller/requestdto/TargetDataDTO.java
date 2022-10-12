package fisolution.jsonProject.controller.requestdto;

import fisolution.jsonProject.entity.DataStatus;
import fisolution.jsonProject.entity.TargetData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TargetDataDTO {

    private String fileName;
    private String imageId;
    private String dataSetName;
    private Integer annotationCnt;
    private String objectName;
    private String superCategory;
    // 이 부분은 논의 필요 server 에서 할 것인가 python 쪽에서 할 것인가
    private DataStatus status;

    private TargetResultDTO targetResultDTO;

    public TargetData toEntity(){
        return TargetData.builder()
                .fileName(fileName)
                .imageId(imageId)
                .dataSetName(dataSetName)
                .annotationCnt(annotationCnt)
                .objectName(objectName)
                .superCategory(superCategory)
                .build();
    }
}
