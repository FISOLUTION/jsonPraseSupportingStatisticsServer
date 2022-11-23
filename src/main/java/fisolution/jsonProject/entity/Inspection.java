package fisolution.jsonProject.entity;

import fisolution.jsonProject.controller.requestdto.InspectionDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 이미지 박스 ==> JSON 으로 변환한 데이터
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "INSPECTION")
public class Inspection {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageFileName; // 이미지 파일 네임 ex) IMG_0000001_car(sedan).jpg

    private String dataSetName;         // 4-1 , 4-2 같은 내용 (과제 번호)

    private Integer annotationCnt;      // annotation 관련 갯수

    @OneToMany(mappedBy = "inspection")
    private List<AnnotationData> annotationList = new ArrayList<>();

    @Builder
    public Inspection(Long id, String imageFileName, String dataSetName, Integer annotationCnt, List<AnnotationData> annotationList) {
        this.id = id;
        this.imageFileName = imageFileName;
        this.dataSetName = dataSetName;
        this.annotationCnt = annotationCnt;
        this.annotationList = annotationList;
    }

    public Inspection(InspectionDTO inspectionDTO) {
        this.imageFileName = inspectionDTO.getImageFileName();
        this.dataSetName = inspectionDTO.getDataSetName();
        this.annotationCnt = inspectionDTO.getAnnotationList().size();
    }
}
