package fisolution.jsonProject.entity;

import fisolution.jsonProject.entity.enumtype.DataStatus;
import fisolution.jsonProject.entity.enumtype.InspectionType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class InspectionResult {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private InspectionType inspectionType;
    private DataStatus dataStatus;

    @JoinColumn
    @ManyToOne
    private TargetData targetData;

}
