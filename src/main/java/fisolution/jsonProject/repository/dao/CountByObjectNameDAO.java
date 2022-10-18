package fisolution.jsonProject.repository.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class CountByObjectNameDAO {

    private Long objectId;
    private String category;
    private String superCategory;
    private String inOut;
    private Long jsonCnt;
    private Long annotationCnt;

    public CountByObjectNameDAO(Long objectId, String category, String superCategory, String inOut, Long jsonCnt, Long annotationCnt) {
        this.objectId = objectId;
        this.category = category;
        this.superCategory = superCategory;
        this.inOut = inOut;
        this.jsonCnt = jsonCnt;
        if(annotationCnt == null){
            this.jsonCnt = 0L;
            this.annotationCnt = 0L;
        } else{
            this.annotationCnt = annotationCnt;
        }
    }
}
