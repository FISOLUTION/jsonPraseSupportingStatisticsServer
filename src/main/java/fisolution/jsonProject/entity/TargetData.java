package fisolution.jsonProject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TargetData extends BaseEntity {

    @Id @GeneratedValue
    private Long id;                    // 인조키
    private String imageId;             // 이미지 아이디
    private String fileName;            // json 파일명
    private String objectName;          // 오브젝트 명칭
    private String superCategory;       // 오브젝트 명칭의 상위 카테고리
    private Integer annotationCnt;      // annotation 관련 갯수
    private String dataSetName;         // 4-1 , 4-2 같은 내용

    @Enumerated(EnumType.STRING)
    private DataStatus status;          // 종합 상태

    @OneToOne
    @JoinColumn(unique = true)
    private TargetResults result;       // 결과 매핑

    @Builder
    public TargetData(Long id, String imageId, String fileName, String objectName, String superCategory, Integer annotationCnt, String dataSetName, DataStatus status) {
        this.id = id;
        this.imageId = imageId;
        this.fileName = fileName;
        this.objectName = objectName;
        this.superCategory = superCategory;
        this.annotationCnt = annotationCnt;
        this.dataSetName = dataSetName;
        this.status = status;
    }
}
