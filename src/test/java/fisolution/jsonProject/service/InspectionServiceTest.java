package fisolution.jsonProject.service;

import fisolution.jsonProject.controller.requestdto.InspectionDTO;
import fisolution.jsonProject.entity.AnnotationData;
import fisolution.jsonProject.entity.Inspection;
import fisolution.jsonProject.repository.BulkInsertRepository;
import fisolution.jsonProject.repository.InspectionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class InspectionServiceTest {

    @InjectMocks
    InspectionService inspectionService;

    @Mock
    InspectionRepository inspectionRepository;

    @Mock
    BulkInsertRepository bulkInsertRepository;

    Inspection inspection;
    AnnotationData annotation1;
    AnnotationData annotation2;
    AnnotationData annotation3;

    @BeforeEach
    public void before() {
        annotation1 = AnnotationData.builder()
                .id(1L)
                .annotationId("590")
                .objectId("31")
                .iou("-0.19131811520206435")
                .build();
        annotation2 = AnnotationData.builder()
                .id(2L)
                .annotationId("590")
                .objectId("32")
                .iou("-0.04415546827016537")
                .build();
        annotation3 = AnnotationData.builder()
                .id(3L)
                .annotationId("590")
                .objectId("33")
                .iou("0.15973454302223952")
                .build();

        List<AnnotationData> annotationList = List.of(this.annotation1, annotation2, annotation3);

        inspection = Inspection.builder()
                .id(4L)
                .imageFileName("IMG_0000001_car(sedan).jpg")
                .dataSetName("4-1")
                .annotationList(annotationList)
                .build();
    }

    @Test
    public void save() throws Exception {
        //given
        given(inspectionRepository.save(any()))
                .willReturn(inspection);

        InspectionDTO request = InspectionDTO.builder()
                .imageFileName("IMG_0000001_car(sedan).jpg")
                .dataSetName("4-1")
                .annotationList(new ArrayList<>())
                .build();
        //when
        Long savedId = inspectionService.save(request);
        //then
        Assertions.assertThat(savedId).isEqualTo(inspection.getId());
    }

}