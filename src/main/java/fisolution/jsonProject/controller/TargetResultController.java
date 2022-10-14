package fisolution.jsonProject.controller;

import fisolution.jsonProject.controller.requestdto.SearchRequestDTO;
import fisolution.jsonProject.controller.requestdto.TargetDataDTO;
import fisolution.jsonProject.controller.responsedto.TargetDataResponseDTO;
import fisolution.jsonProject.controller.responsedto.TargetDataSearchResponseDTO;
import fisolution.jsonProject.service.StatisticUtils;
import fisolution.jsonProject.service.TargetDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TargetResultController {
    /**
     * 저장, 검색, 단건 검색 필요 저장 spec 정의 할 것
     */

    private final TargetDataService targetDataService;
    private final StatisticUtils statisticUtils;

    @PostMapping("/data")
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@RequestBody @Validated TargetDataDTO dto){
        return targetDataService.save(dto);
    }

    /**
     * 단건 검색 내용 오류 내용들 표시, 원문 표시,
     */
    @GetMapping("/data/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TargetDataResponseDTO find(@PathVariable Long id){
        return targetDataService.findById(id);
    }

    @GetMapping("/search/data")
    @ResponseStatus(HttpStatus.OK)
    public Page<TargetDataSearchResponseDTO> search(@ModelAttribute SearchRequestDTO dto,
                                                    @PageableDefault(page = 0, size = 10, sort = {"imageId", "id"}) Pageable pageable){
        return targetDataService.findAll(dto, pageable);
    }

    @GetMapping("/statistics")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> statistic(@RequestParam String dataSetName){
        return statisticUtils.overall(dataSetName);
    }

}
