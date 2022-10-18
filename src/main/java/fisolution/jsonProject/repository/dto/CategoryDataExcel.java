package fisolution.jsonProject.repository.dto;

import fisolution.jsonProject.entity.CategoryData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDataExcel {

    private String id;
    private String categoryName;
    private String superCategoryName;
    private String inOut;
    private String kind;

    public static CategoryData createCategoryData(CategoryDataExcel categoryDataExcel){
        Double id = Double.parseDouble(categoryDataExcel.id);
        return new CategoryData(id.longValue(),
                categoryDataExcel.getCategoryName(),
                categoryDataExcel.getSuperCategoryName(),
                categoryDataExcel.getInOut(),
                categoryDataExcel.getKind()
                );
    }
}
