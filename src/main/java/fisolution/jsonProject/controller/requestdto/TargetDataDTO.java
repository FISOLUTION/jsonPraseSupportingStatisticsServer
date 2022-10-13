package fisolution.jsonProject.controller.requestdto;

import fisolution.jsonProject.entity.DataStatus;
import fisolution.jsonProject.entity.TargetData;
import fisolution.jsonProject.entity.TargetResults;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TargetDataDTO {

    @NotNull
    private String fileName;
    @NotNull
    private String imageId;
    @NotNull
    private String dataSetName;
    @NotNull
    private Integer annotationCnt;
    @NotNull
    private String objectName;
    @NotNull
    private String superCategory;
    // 이 부분은 논의 필요 server 에서 할 것인가 python 쪽에서 할 것인가
    @NotNull
    private DataStatus status;
    @Valid
    private TargetResultDTO targetResult;

    public TargetData toEntity(TargetResults targetResults){
        return TargetData.builder()
                .fileName(fileName)
                .imageId(imageId)
                .dataSetName(dataSetName)
                .annotationCnt(annotationCnt)
                .objectName(objectName)
                .status(status)
                .superCategory(superCategory)
                .result(targetResults)
                .build();
    }
}
