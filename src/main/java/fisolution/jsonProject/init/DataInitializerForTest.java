package fisolution.jsonProject.init;

import fisolution.jsonProject.entity.DataStatus;
import fisolution.jsonProject.entity.TargetData;
import fisolution.jsonProject.entity.TargetResults;
import fisolution.jsonProject.repository.TargetDataRepository;
import fisolution.jsonProject.repository.TargetResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DataInitializerForTest {

    private final TargetDataRepository targetDataRepository;
    private final TargetResultRepository targetResultRepository;

    @PostConstruct
    private void postConstruct(){
        init();
    }

    @Transactional
    public void init(){

        for(int i = 0; i < 100; i++) {
            TargetResults targetResults = new TargetResults("orginalFile", DataStatus.ERROR, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS);
            // id 값 넣으면 merge 일어나긴 하는데...
            TargetData targetData = new TargetData("imageId", "fileName", "objectName", "superCategory", 3, "dataSetName", DataStatus.PASS, targetResults);
            targetResultRepository.save(targetResults);
            targetDataRepository.save(targetData);
        }
    }

}
