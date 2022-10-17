package fisolution.jsonProject.repository.dao;

import fisolution.jsonProject.entity.enumtype.DataStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CountDAO {

    String inspectName;
    DataStatus dataStatus;
    Long cnt;

}
