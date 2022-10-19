package fisolution.jsonProject.service;

import fisolution.jsonProject.controller.requestdto.SearchRequestDTO;
import fisolution.jsonProject.controller.requestdto.TargetDataDTO;
import fisolution.jsonProject.controller.responsedto.TargetDataResponseDTO;
import fisolution.jsonProject.controller.responsedto.TargetDataSearchResponseDTO;
import fisolution.jsonProject.entity.*;
import fisolution.jsonProject.repository.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
public class TargetDataService {

    private final TargetDataRepository targetDataRepository;
    private final TargetResultRepository targetResultRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryDataRepository categoryDataRepository;
    private final BulkInsertRepository bulkInsertRepository;
    private final DynamicQuery dynamicQuery;

    public Long time = 0L;
    public Long time2 = 0L;

    @Transactional
    public Long save(TargetDataDTO dto) {
        long start = System.currentTimeMillis();
        // 각각 toEntity 로 분리 작업
        TargetResults targetResults = dto.getTargetResult().toEntity();
        TargetData targetData = dto.toEntity(targetResults);
        targetResults.injectTargetData(targetData);
        List<Category> categories = dto.getCategories().stream()
                .map((categoryDTO) -> {
                    CategoryData categoryData = categoryDataRepository
                            .getReferenceById(categoryDTO.getCategoryDataId());
                    return categoryDTO.toEntity(targetData, categoryData);
                })
                        .collect(Collectors.toList());

        // save 하기 cascade 써도 되지만 명시적으로 사용하기 위해서 그냥 분리 작업 실시
        targetDataRepository.save(targetData);
        targetResultRepository.save(targetResults);
        categoryRepository.saveAll(categories);

        long end = System.currentTimeMillis();
        time += end - start;

        return targetData.getId();
    }

    @Transactional
    public Long save2(TargetDataDTO dto){
        long start = System.currentTimeMillis();
        TargetResults targetResults = dto.getTargetResult().toEntity();
        TargetData targetData = dto.toEntity(targetResults);
        targetResults.injectTargetData(targetData);
        List<Category> categories = dto.getCategories().stream()
                .map((categoryDTO) -> {
                    CategoryData categoryData = categoryDataRepository
                            .getReferenceById(categoryDTO.getCategoryDataId());
                    return categoryDTO.toEntity(targetData, categoryData);
                })
                .collect(Collectors.toList());
        List<InspectionResult> inspectionResults = targetResults.createInspectionResults();

        targetDataRepository.save(targetData);
        targetResultRepository.save(targetResults);
        bulkInsertRepository.bulkInsertCategory(categories);
        bulkInsertRepository.bulkInsertInspectionResult(inspectionResults);
        long end = System.currentTimeMillis();
        time += end - start;
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
