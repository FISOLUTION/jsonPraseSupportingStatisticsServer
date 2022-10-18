package fisolution.jsonProject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "targetDataId")
    private TargetData targetData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryDataId")
    private CategoryData categoryData;

    public Category(TargetData targetData, CategoryData categoryData) {
        this.targetData = targetData;
        this.categoryData = categoryData;
    }
}
