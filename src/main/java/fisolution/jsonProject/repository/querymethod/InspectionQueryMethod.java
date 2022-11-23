package fisolution.jsonProject.repository.querymethod;

import com.querydsl.core.types.dsl.BooleanExpression;

import static fisolution.jsonProject.entity.QInspection.*;


public class InspectionQueryMethod {

    protected BooleanExpression dataSetNameEq(String dataSetName) {
        return dataSetName == null || dataSetName.equals("") ?
                null : inspection.dataSetName.eq(dataSetName);
    }

    protected BooleanExpression imageFileNameContains(String imageFileName) {
        return imageFileName == null || imageFileName.equals("") ?
                null : inspection.imageFileName.contains(imageFileName);
    }
}
