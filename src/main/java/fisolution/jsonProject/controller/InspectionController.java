package fisolution.jsonProject.controller;

import fisolution.jsonProject.controller.requestdto.InspectionDTO;
import fisolution.jsonProject.controller.requestdto.SearchInspectionRequestDTO;
import fisolution.jsonProject.controller.requestdto.SearchRequestDTO;
import fisolution.jsonProject.controller.responsedto.InspectionResponse;
import fisolution.jsonProject.entity.Inspection;
import fisolution.jsonProject.service.InspectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class InspectionController {

    private final InspectionService inspectionService;

    @PostMapping("/inspections")
    public Long save(@RequestBody @Validated InspectionDTO inspectionDTO) {
        return inspectionService.save(inspectionDTO);
    }

    @GetMapping("/inspections/{id}")
    public InspectionResponse findById(@PathVariable Long id) {
        return inspectionService.findById(id);
    }

    @GetMapping("/inspections")
    public Page<InspectionResponse> search(@ModelAttribute SearchInspectionRequestDTO request,
                                           @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return inspectionService.search(request, pageable);
    }

}
