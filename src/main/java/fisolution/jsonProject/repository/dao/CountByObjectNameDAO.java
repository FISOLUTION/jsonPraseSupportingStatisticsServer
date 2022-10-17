package fisolution.jsonProject.repository.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CountByObjectNameDAO {

    private String category;
    private String superCategory;
    private long jsonCnt;
    private long annotationCnt;

}
