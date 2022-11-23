package fisolution.jsonProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fisolution.jsonProject.controller.requestdto.AnnotationDTO;
import fisolution.jsonProject.controller.requestdto.InspectionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class InspectionControllerTest {

    @InjectMocks
    InspectionController inspectionController;

    InspectionDTO request;
    AnnotationDTO annotation1;
    AnnotationDTO annotation2;
    AnnotationDTO annotation3;

    MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(inspectionController)
                .build();

        objectMapper = new ObjectMapper();
        annotation1 = AnnotationDTO.builder()
                .annotationId("590")
                .objectId("31")
                .iou("-0.19131811520206435")
                .build();
        annotation2 = AnnotationDTO.builder()
                .annotationId("590")
                .objectId("32")
                .iou("-0.04415546827016537")
                .build();
        annotation3 = AnnotationDTO.builder()
                .annotationId("590")
                .objectId("33")
                .iou("0.15973454302223952")
                .build();

        List<AnnotationDTO> annotationList = List.of(this.annotation1, annotation2, annotation3);

        request = InspectionDTO.builder()
                .imageFileName("IMG_0000001_car(sedan).jpg")
                .dataSetName("4-1")
                .annotationList(annotationList)
                .build();
    }


    @Test
    public void save() throws Exception {
        //given

        //when
        mockMvc.perform(post("/inspections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(
                                objectMapper.writeValueAsString(request)
                        )
                )
                .andExpect(status().isOk())
                .andDo(print());
        //then

    }
}