package fisolution.jsonProject.controller.requestdto;

import fisolution.jsonProject.entity.Category;
import fisolution.jsonProject.entity.CategoryData;
import fisolution.jsonProject.entity.TargetData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long categoryDataId;
    private String superCategory;
    private String category;

    public Category toEntity(TargetData targetData, CategoryData categoryData){
        return new Category(targetData, categoryData);
    }
}
