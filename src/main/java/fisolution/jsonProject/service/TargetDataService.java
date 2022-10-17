package fisolution.jsonProject.service;

import fisolution.jsonProject.controller.requestdto.SearchRequestDTO;
import fisolution.jsonProject.controller.requestdto.TargetDataDTO;
import fisolution.jsonProject.controller.responsedto.TargetDataResponseDTO;
import fisolution.jsonProject.controller.responsedto.TargetDataSearchResponseDTO;
import fisolution.jsonProject.entity.Category;
import fisolution.jsonProject.entity.TargetData;
import fisolution.jsonProject.entity.TargetResults;
import fisolution.jsonProject.repository.CategoryRepository;
import fisolution.jsonProject.repository.DynamicQuery;
import fisolution.jsonProject.repository.TargetDataRepository;
import fisolution.jsonProject.repository.TargetResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TargetDataService {

    private final TargetDataRepository targetDataRepository;
    private final TargetResultRepository targetResultRepository;
    private final CategoryRepository categoryRepository;
    private final DynamicQuery dynamicQuery;

    @Transactional
    public Long save(TargetDataDTO dto) {
        // 각각 toEntity 로 분리 작업
        TargetResults targetResults = dto.getTargetResult().toEntity();
        TargetData targetData = dto.toEntity(targetResults);
        List<Category> categories = dto.getCategories().stream()
                .map((categoryDTO) -> categoryDTO.toEntity(targetData))
                        .collect(Collectors.toList());

        // save 하기 cascade 써도 되지만 명시적으로 사용하기 위해서 그냥 분리 작업 실시
        targetDataRepository.save(targetData);
        targetResultRepository.save(targetResults);
        categoryRepository.saveAll(categories);

        return targetData.getId();
    }


    @Transactional(readOnly = true)
    public TargetDataResponseDTO findById(Long id) {
        return targetDataRepository.findByIdWithResult(id);
    }

    @Transactional(readOnly = true)
    public Page<TargetDataSearchResponseDTO> findAll(SearchRequestDTO dto, Pageable pageable) {
        return dynamicQuery.mainSearch(dto, pageable);
    }
}
