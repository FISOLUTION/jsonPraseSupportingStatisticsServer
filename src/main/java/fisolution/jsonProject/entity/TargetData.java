package fisolution.jsonProject.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TargetData {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String imageId;

    @OneToMany(mappedBy = "targetData")
    List<TargetDataSet> targetDataSets = new ArrayList<>();

}
