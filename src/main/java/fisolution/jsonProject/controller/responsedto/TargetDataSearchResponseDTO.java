package fisolution.jsonProject.controller.responsedto;

import com.querydsl.core.annotations.QueryProjection;
import fisolution.jsonProject.entity.DataStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TargetDataSearchResponseDTO {

    private String dataSetName;
    private String imageId;
    private String fileName;
    private String objectName;
    private DataStatus dataStatus;
    private LocalDateTime lastUpdated;

    @QueryProjection
    public TargetDataSearchResponseDTO(String dataSetName, String imageId, String fileName, String objectName, DataStatus dataStatus, LocalDateTime lastUpdated) {
        this.dataSetName = dataSetName;
        this.imageId = imageId;
        this.fileName = fileName;
        this.objectName = objectName;
        this.dataStatus = dataStatus;
        this.lastUpdated = lastUpdated;
    }
}
