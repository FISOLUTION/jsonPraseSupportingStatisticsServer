package fisolution.jsonProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Transaction AOP 내부 호출 이슈로 인해 따로 빼서 취합함
 */
@Component
@RequiredArgsConstructor
public class StatisticUtils {

    private final StatisticService statisticService;

    @Transactional
    public Map<String, Object> overall(String dataSetName){
        Map<String, Object> result = new HashMap<>();

        result.put("json 건수 조회", statisticService.statisticOverview(dataSetName));
        result.put("검사유형별 품질", statisticService.inspectionResultLegacy(dataSetName));
        result.put("클래스별 건수", statisticService.countPerObjectName(dataSetName));

        return result;
    }
}
