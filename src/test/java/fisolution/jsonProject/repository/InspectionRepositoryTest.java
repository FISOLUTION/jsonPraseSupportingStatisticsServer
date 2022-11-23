package fisolution.jsonProject.repository;

import fisolution.jsonProject.entity.Inspection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class InspectionRepositoryTest {

    @Autowired
    InspectionRepository inspectionRepository;

    @Test
    public void save() throws Exception {
        //given
        Inspection inspection = Inspection.builder()
                .imageFileName("IMG_0000001_car(sedan).jpg")
                .dataSetName("4-1")
                .annotationList(new ArrayList<>())
                .build();
        //when

        //then
        inspectionRepository.save(inspection);
    }

}