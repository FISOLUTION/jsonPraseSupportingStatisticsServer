package fisolution.jsonProject.entity;

import fisolution.jsonProject.entity.enumtype.DataStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TARGETDATA")
public class TargetData extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 인조키
    private String imageId;             // 이미지 아이디
    private String fileName;            // json 파일명
    private Integer annotationCnt;      // annotation 관련 갯수
    private String dataSetName;         // 4-1 , 4-2 같은 내용
    private String objectName;

    @Enumerated(EnumType.STRING)
    private DataStatus status;          // 종합 상태

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private TargetResults result;       // 결과 매핑

    @OneToMany(mappedBy = "targetData")
    private List<Category> categories;

    @Builder
    public TargetData(String imageId, String fileName, String objectName, Integer annotationCnt, String dataSetName, DataStatus status, TargetResults targetResults) {
        this.imageId = imageId;
        this.fileName = fileName;
        this.objectName = objectName;
        this.annotationCnt = annotationCnt;
        this.dataSetName = dataSetName;
        this.status = status;
        this.result = targetResults;
    }
}
