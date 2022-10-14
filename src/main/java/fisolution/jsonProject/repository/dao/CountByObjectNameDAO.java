package fisolution.jsonProject.repository.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountByObjectNameDAO {

    private String objectName;
    private String superCategory;
    private long jsonCnt;
    private long annotationCnt;

}
