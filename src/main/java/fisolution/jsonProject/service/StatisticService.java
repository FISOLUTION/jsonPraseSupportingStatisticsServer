package fisolution.jsonProject.service;

import fisolution.jsonProject.repository.TargetDataRepository;
import fisolution.jsonProject.repository.TargetResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final TargetDataRepository targetDataRepository;
    private final TargetResultRepository targetResultRepository;

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
     *
     * 즉 쿼리 한방 해결 OK 다른 것 끼리 묶을 수 있는지 살펴 볼 것
     */


}
