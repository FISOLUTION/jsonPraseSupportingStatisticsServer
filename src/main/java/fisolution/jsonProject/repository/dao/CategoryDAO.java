package fisolution.jsonProject.repository.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryDAO {

    private String superCategory;
    private String category;
    private Long targetDataId;

}
