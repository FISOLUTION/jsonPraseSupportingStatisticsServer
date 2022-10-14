package fisolution.jsonProject.repository.dao;

import fisolution.jsonProject.entity.DataStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CountDAO {

    String inspectName;
    DataStatus dataStatus;
    Long cnt;

}
