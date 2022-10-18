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
public class CategoryData {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String categoryName;
    private String superCategoryName;
    private String inOut;
    private String kind;

    @OneToMany(mappedBy = "categoryData")
    private List<Category> categoryList = new ArrayList<>();

    public CategoryData(Long id, String categoryName, String superCategoryName, String inOut, String kind) {
        this.id = id;
        this.categoryName = categoryName;
        this.superCategoryName = superCategoryName;
        this.inOut = inOut;
        this.kind = kind;
    }
}
