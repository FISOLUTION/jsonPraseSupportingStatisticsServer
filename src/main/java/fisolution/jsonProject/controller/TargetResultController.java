package fisolution.jsonProject.controller;

import fisolution.jsonProject.controller.requestdto.TargetDataDTO;
import fisolution.jsonProject.service.TargetDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TargetResultController {
    /**
     * 저장, 검색, 단건 검색 필요 저장 spec 정의 할 것
     */

    private TargetDataService targetDataService;

    @PostMapping("/data")
    public void save(@RequestBody TargetDataDTO dto){
        targetDataService.save(dto);
    }

    /**
     * 단건 검색 내용 오류 내용들 표시, 원문 표시,
     */
    @GetMapping("/data/{id}")
    public void find(@PathVariable Long id){
        targetDataService.findById(id);
    }

}
