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
    private String superCategory;
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private TargetData targetData;


    public Category(String superCategory, String category, TargetData targetData) {
        this.superCategory = superCategory;
        this.category = category;
        this.targetData = targetData;
    }

}
