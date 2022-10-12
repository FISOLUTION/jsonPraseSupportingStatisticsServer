package fisolution.jsonProject.service;

import fisolution.jsonProject.controller.requestdto.TargetDataDTO;
import fisolution.jsonProject.entity.TargetData;
import fisolution.jsonProject.entity.TargetResults;
import fisolution.jsonProject.repository.TargetDataRepository;
import fisolution.jsonProject.repository.TargetResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TargetDataService {

    TargetDataRepository targetDataRepository;
    TargetResultRepository targetResultRepository;

    @Transactional
    public void save(TargetDataDTO dto) {
        // 각각 toEntity 로 분리 작업
        TargetData targetData = dto.toEntity();
        TargetResults targetResults = dto.getTargetResultDTO().toEntity();

        // save 하기 cascade 써도 되지만 명시적으로 사용하기 위해서 그냥 분리 작업 실시
        targetDataRepository.save(targetData);
        targetResultRepository.save(targetResults);
    }


    @Transactional(readOnly = true)
    public void findById(Long id) {

    }
}
