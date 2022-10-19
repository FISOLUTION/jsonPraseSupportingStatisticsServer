package fisolution.jsonProject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "CATEGORYDATA")
public class CategoryData {

    @Id
    private Long id;
    private String categoryName;
    private String superCategoryName;
    private String in_out;
    private String kind;

    @OneToMany(mappedBy = "categoryData")
    private List<Category> categoryList = new ArrayList<>();

    public CategoryData(Long id, String categoryName, String superCategoryName, String in_out, String kind) {
        this.id = id;
        this.categoryName = categoryName;
        this.superCategoryName = superCategoryName;
        this.in_out = in_out;
        this.kind = kind;
    }
}
