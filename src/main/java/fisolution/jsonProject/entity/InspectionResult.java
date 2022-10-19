package fisolution.jsonProject.entity;

import fisolution.jsonProject.entity.enumtype.DataStatus;
import fisolution.jsonProject.entity.enumtype.InspectionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "INSPECTIONRESULT")
public class InspectionResult {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private InspectionType inspectionType;
    @Enumerated(EnumType.STRING)
    private DataStatus dataStatus;

    @JoinColumn
    @ManyToOne
    private TargetData targetData;

    public InspectionResult(InspectionType inspectionType, DataStatus dataStatus, TargetData targetData) {
        this.inspectionType = inspectionType;
        this.dataStatus = dataStatus;
        this.targetData = targetData;
    }
}
