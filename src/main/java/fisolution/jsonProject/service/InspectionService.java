package fisolution.jsonProject.service;

import fisolution.jsonProject.controller.requestdto.AnnotationDTO;
import fisolution.jsonProject.controller.requestdto.InspectionDTO;
import fisolution.jsonProject.controller.requestdto.SearchInspectionRequestDTO;
import fisolution.jsonProject.controller.requestdto.SearchRequestDTO;
import fisolution.jsonProject.controller.responsedto.InspectionResponse;
import fisolution.jsonProject.entity.AnnotationData;
import fisolution.jsonProject.entity.Inspection;
import fisolution.jsonProject.repository.BulkInsertRepository;
import fisolution.jsonProject.repository.InspectionDynamicQuery;
import fisolution.jsonProject.repository.InspectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InspectionService {

    private final InspectionRepository inspectionRepository;
    private final BulkInsertRepository bulkInsertRepository;
    private final InspectionDynamicQuery dynamicQuery;

    public Long save(InspectionDTO inspectionDTO) {
        Inspection inspection = new Inspection(inspectionDTO);
        Inspection savedInspection = inspectionRepository.save(inspection);

        List<AnnotationDTO> annotationList = inspectionDTO.getAnnotationList();
        List<AnnotationData> annotationDataList = annotationList.stream()
                .map(a -> new AnnotationData(a, savedInspection))
                .collect(Collectors.toList());

        bulkInsertRepository.bulkInsertAnnotationData(annotationDataList);
        return savedInspection.getId();
    }

    public InspectionResponse findById(Long id) {
        Inspection inspection = inspectionRepository.findByIdWithAnnotation(id)
                .orElseThrow(() -> new NoSuchElementException("ID ERROR"));

        return new InspectionResponse(inspection);
    }

    public Page<InspectionResponse> search(SearchInspectionRequestDTO request, Pageable pageable) {
        Page<Inspection> result = dynamicQuery.search(request, pageable);
        return result.map(InspectionResponse::new);
    }
}
