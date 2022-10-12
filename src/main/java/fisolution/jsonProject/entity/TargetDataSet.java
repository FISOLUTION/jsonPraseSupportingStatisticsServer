package fisolution.jsonProject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TargetDataSet {

    @Id @GeneratedValue
    private Long id;

    private String objectName;
    private String dataSetName;

    @Enumerated(EnumType.STRING)
    private DataStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private TargetData targetData;

    @OneToOne
    @JoinColumn(unique = true)
    private TargetResults result;

}
