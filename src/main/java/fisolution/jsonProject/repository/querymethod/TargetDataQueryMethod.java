package fisolution.jsonProject.repository.querymethod;

import com.querydsl.core.types.dsl.BooleanExpression;
import fisolution.jsonProject.entity.enumtype.DataStatus;

import static fisolution.jsonProject.entity.QTargetData.targetData;

public class TargetDataQueryMethod {

    protected BooleanExpression dataSetNameEq(String dataSetName){
        return dataSetName == null || dataSetName.equals("") ?
                null : targetData.dataSetName.eq(dataSetName);
    }

    protected BooleanExpression objectNameLike(String objectName){
        return objectName == null || objectName.equals("") ?
                null : targetData.objectName.like(objectName);
    }

    protected BooleanExpression imageIdLike(String imageId){
        return imageId == null || imageId.equals("") ?
                null : targetData.imageId.like(imageId);
    }

    protected BooleanExpression dataStatusEq(DataStatus dataStatus){
        return dataStatus == null ?
                null : targetData.status.eq(dataStatus);
    }

}
