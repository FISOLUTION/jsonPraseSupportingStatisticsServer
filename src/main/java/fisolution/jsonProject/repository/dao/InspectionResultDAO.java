package fisolution.jsonProject.repository.dao;

import fisolution.jsonProject.entity.InspectionResult;
import fisolution.jsonProject.entity.TargetData;
import fisolution.jsonProject.entity.TargetResults;
import fisolution.jsonProject.entity.enumtype.DataStatus;
import fisolution.jsonProject.entity.enumtype.InspectionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class InspectionResultDAO {

    private Long id;
    private InspectionType inspectionType;
    private DataStatus dataStatus;

    // foreign key for result Data
    private Long resultDataID;

    @Builder
    public InspectionResultDAO(InspectionType inspectionType, DataStatus dataStatus, Long resultDataID) {
        this.inspectionType = inspectionType;
        this.dataStatus = dataStatus;
        this.resultDataID = resultDataID;
    }

    public static InspectionResultDAO createInspectResult(Long targetDataID, InspectionType inspectionType, DataStatus dataStatus){
        return InspectionResultDAO.builder()
                .inspectionType(inspectionType)
                .dataStatus(dataStatus)
                .resultDataID(targetDataID)
                .build();
    }


    public static List<InspectionResultDAO> createInspectionList(TargetData targetData){
        List<InspectionResultDAO> list = new ArrayList<>();

        TargetResults targetResults = targetData.getResult();

        return list;
    }
}
