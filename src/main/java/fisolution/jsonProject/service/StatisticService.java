package fisolution.jsonProject.service;

import fisolution.jsonProject.controller.responsedto.InspectResultDTO;
import fisolution.jsonProject.entity.DataStatus;
import fisolution.jsonProject.repository.CountQueryLegacy;
import fisolution.jsonProject.repository.TargetDataRepository;
import fisolution.jsonProject.repository.TargetResultRepository;
import fisolution.jsonProject.repository.dao.CountByObjectNameDAO;
import fisolution.jsonProject.repository.dao.CountDAO;
import fisolution.jsonProject.repository.dao.OverviewDAO;
import fisolution.jsonProject.repository.dto.OverviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final TargetDataRepository targetDataRepository;
    private final TargetResultRepository targetResultRepository;
    private final CountQueryLegacy countQueryLegacy;

    /**
     * overAll data 조회 : JSON 건수, Annotation 건수, Json 1건당 평균 annotation, 순도율, 오류율, 오류 건수
     * 현재 통계 처리는 ObjectName 으로 건수 조회 기능 + 모든 ObjectName 에 대한 건 수 도 통계처리 필요
     */

    /**
     * OverView 통계 처리 JSON 건수, Annotation 건수, Json 1건당 Annotations, 순도유르 오류율 오류 건수
     * 쿼리 한방으로 가능한가?
     * 1. select t.status, count(t.id), sum(annotationCnt) from targetData as t group by t.status;
     * => Json 건수 각 count 를 합친 값으로 해결 OK => Service 에서 짬뽕
     * => Annotation 건수 또한 마찬가지 sum 으로 가져온 것으로 해결
     * => Json 1건당 Annotations 는 위에 JSON 건수, Annotation 건수 2개로 해결 가능
     * => 순도율, 오류율 또한 마찬 가지 Status 별 갯수를 가지고 있으므로
     * <p>
     * 즉 쿼리 한방 해결 OK 다른 것 끼리 묶을 수 있는지 살펴 볼 것
     * <p>
     * ++ 추가 요구사항 변경 가능성으로 인해 통계 표 하나당 하나의 repository 메서드를 구현하는 것이 좋겠다.
     *
     * @return
     */
    public OverviewDTO statisticOverview(String dataSetName){
        Map<DataStatus, OverviewDAO> map = targetDataRepository.statisticOverviewQuery(dataSetName)
                .stream()
                .collect(Collectors.toMap(OverviewDAO::getStatus, (overviewDAO) -> overviewDAO));

        OverviewDAO error = map.getOrDefault(DataStatus.ERROR,
                new OverviewDAO(DataStatus.ERROR, 0L, 0L));
        OverviewDAO pass = map.getOrDefault(DataStatus.PASS,
                new OverviewDAO(DataStatus.PASS, 0L, 0L));

        // 해당 ObjectName 의 JSON 총 건수
        long totalCnt = error.getTotalCnt() + pass.getTotalCnt();
        // 해당 ObjectName 의 총 annotation 건 수
        long totalAnnotationCnt = error.getTotalAnnotationCnt() + pass.getTotalAnnotationCnt();
        // 오류율, 순도율
        double errorRate = Math.round((double)error.getTotalCnt() / totalCnt);
        double passRate = Math.round((double)pass.getTotalCnt() / totalCnt);
        // JSON 건당 Annotation 수
        double avgAnnotation = Math.round((double)totalAnnotationCnt / totalCnt);

        return new OverviewDTO(totalCnt, totalAnnotationCnt, avgAnnotation, passRate, errorRate, error.getTotalCnt());
    }

    /**
     * 검사 항목별 조회 사항... statistic 을 위한 domain 추가 작성? => insert 검사 항목당 나가면 큰 overhead...
     * 그렇다면 모든 검사유형별 query 작성?... 더 아닌 것 같다.
     * application level 에서 가공? => 더 아님 대략 400 만개 정도인데 application 단에서 하는 것은 말이 안됨
     * 그렇다면 둘중 하나 선택? 아니면 Native 쿼리중에 있나? window 함수?
     * window 함수로도 해결 불가... partition 으로 쪼개도 딱히 의미가 없음
     * <p>
     * Entity 추가 작성하고 batch insert 를 통한 성능 최적화를 해보자.
     */
    public Map<String, InspectResultDTO> inspectionResultLegacy(String dataSetName){

        List<InspectResultDTO> results = new ArrayList<>();

        results.add(consumeCount(countQueryLegacy.countG011(dataSetName)));
        results.add(consumeCount(countQueryLegacy.countG022(dataSetName)));
        results.add(consumeCount(countQueryLegacy.countG031(dataSetName)));
        results.add(consumeCount(countQueryLegacy.countG041(dataSetName)));
        results.add(consumeCount(countQueryLegacy.countG051(dataSetName)));
        results.add(consumeCount(countQueryLegacy.countG052(dataSetName)));
        results.add(consumeCount(countQueryLegacy.countG053(dataSetName)));
        results.add(consumeCount(countQueryLegacy.countG061(dataSetName)));
        results.add(consumeCount(countQueryLegacy.countG071(dataSetName)));
        results.add(consumeCount(countQueryLegacy.countG081(dataSetName)));
        results.add(consumeCount(countQueryLegacy.countG091(dataSetName)));
        results.add(consumeCount(countQueryLegacy.countG101(dataSetName)));
        results.add(consumeCount(countQueryLegacy.countM011(dataSetName)));
        results.add(consumeCount(countQueryLegacy.countM021(dataSetName)));
        results.add(consumeCount(countQueryLegacy.countM031(dataSetName)));

        return results.stream()
                .collect(Collectors.toMap(
                        InspectResultDTO::getInspectName,
                        inspectResultDTO -> inspectResultDTO
                ));
    }

    public List<CountByObjectNameDAO> countPerObjectName(String dataSetName){
        return targetDataRepository.statisticPerObject(dataSetName);
    }

    private InspectResultDTO consumeCount(List<CountDAO> dao){

        String inspectName = dao.get(0).getInspectName();

        Map<DataStatus, CountDAO> collect = dao.stream()
                .collect(Collectors.toMap(CountDAO::getDataStatus, countDAO -> countDAO));

        CountDAO error = collect.getOrDefault(DataStatus.ERROR, new CountDAO(inspectName, DataStatus.ERROR, 0L));
        CountDAO pass = collect.getOrDefault(DataStatus.PASS, new CountDAO(inspectName, DataStatus.PASS, 0L));

        long errorCnt = error.getCnt();
        long passCnt = pass.getCnt();
        long totalCnt = errorCnt + passCnt;

        return new InspectResultDTO(inspectName, (double)passCnt / totalCnt, errorCnt);

    }


}
