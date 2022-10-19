package fisolution.jsonProject.controller;

import fisolution.jsonProject.controller.requestdto.SearchRequestDTO;
import fisolution.jsonProject.controller.requestdto.TargetDataDTO;
import fisolution.jsonProject.controller.responsedto.InspectResultDTO;
import fisolution.jsonProject.controller.responsedto.TargetDataResponseDTO;
import fisolution.jsonProject.controller.responsedto.TargetDataSearchResponseDTO;
import fisolution.jsonProject.service.StatisticService;
import fisolution.jsonProject.service.StatisticUtils;
import fisolution.jsonProject.service.TargetDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TargetResultController {
    /**
     * 저장, 검색, 단건 검색 필요 저장 spec 정의 할 것
     */

    private final TargetDataService targetDataService;
    private final StatisticUtils statisticUtils;
    private final StatisticService statisticService;

    @PostMapping("/data")
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@RequestBody @Validated TargetDataDTO dto){
        Long save = targetDataService.save2(dto);
        System.out.println("targetDataService = " + targetDataService.getTime());
        return save;
    }

    @PostMapping("/data2")
    @ResponseStatus(HttpStatus.CREATED)
    public Long save2(@RequestBody @Validated TargetDataDTO dto){
        Long save = targetDataService.save(dto);
        System.out.println("targetDataService = " + targetDataService.getTime2());
        return save;
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
        long start = System.currentTimeMillis();
        Map<String, Object> overall = statisticUtils.overall(dataSetName);
        long end = System.currentTimeMillis();
        System.out.println("time" + (end - start));
        return overall;
    }

    @GetMapping("/statistics2")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, InspectResultDTO> statistic2(@RequestParam String dataSetName){
        long start = System.currentTimeMillis();
        return statisticService.inspectionResult(dataSetName);
    }

}
