package fisolution.jsonProject;

import fisolution.jsonProject.controller.responsedto.InspectResultDTO;
import fisolution.jsonProject.entity.CategoryData;
import fisolution.jsonProject.entity.TargetData;
import fisolution.jsonProject.repository.DynamicQuery;
import fisolution.jsonProject.repository.TargetDataRepository;
import fisolution.jsonProject.repository.dto.OverviewDTO;
import fisolution.jsonProject.service.StatisticService;
import org.hibernate.internal.SessionImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Map;

@SpringBootTest
@Import(NativeQueryTest.Inner.class)
public class NativeQueryTest {

    @Autowired
    TargetDataRepository repository;
    @Autowired
    EntityManager em;
    @Autowired
    StatisticService statisticService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Inner inner;

    @Test
    @DisplayName("[success] statisticServiceTest")
    public void statisticServiceTest(){

        // given
        Map<String, InspectResultDTO> map = statisticService.inspectionResultLegacy("dataSetName");
        OverviewDTO overviewDTO = statisticService.statisticOverview("dataSetName");

        // when
        System.out.println("");
        // then
    }

    @Test
    @DisplayName("[success] native Query Test")
    public void test(){
        // given
//        Long aLong = repository.statisticInspection();
//        System.out.println("aLong = " + aLong);
        // when

        // then
    }

    @Test
    @DisplayName("[success] EntityMangerTest")
    @Transactional
    public void EntityMangerTest() throws InterruptedException {
        // given
        em.find(TargetData.class, 1L);
        new Thread(inner::forTest).start();
        Thread.sleep(2000);
        System.out.println(em);
        // when
        EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
        Map<Object, Object> resourceMap = TransactionSynchronizationManager.getResourceMap();
        System.out.println(" ================== ");
        resourceMap.forEach((object, var) -> {
            System.out.println("object = " + object);
            System.out.println("var = " + var);
        });
        System.out.println("entityManagerFactory = " + entityManagerFactory);
        // then
    }

    @Component
    public static class Inner{

        @Autowired
        EntityManager em;

        @Transactional
        public void forTest() {
            em.find(TargetData.class, 1L);
            Map<Object, Object> resourceMap = TransactionSynchronizationManager.getResourceMap();
            resourceMap.forEach((object, var) -> {
                System.out.println("object = " + object);
                System.out.println("var = " + var);
            });
        }
    }

    @Test
    @DisplayName("[success] insertQueryTest")
    @Transactional
    public void insertQueryTest(){
        // given
        TargetData targetData = new TargetData();
        TargetData targetData1 = new TargetData();

        em.persist(targetData);
        System.out.println("쿼리 즉시?");
        em.persist(targetData1);
        // identity 전략 쿼리 즉시 나감

        CategoryData categoryData = new CategoryData();
        em.persist(categoryData);

        System.out.println("쿼리 즉시?");
        CategoryData categoryData1 = new CategoryData();
        em.persist(categoryData1);
//        jdbcTemplate.execute("get next sequence");
//        jdbcTemplate.update("insert into CategoryData (CATEGORYNAME) VALUES ('ddd')");
        // 수동 키생성 전략 쿼리 미룬다. 물론 jpaRepository 의 경우는 좀 다름 em.persist() 밖에 존재하지 않는데 처리 방안?
        // em.persist() 지연이 자동으로 bulk 처리를 하는가?
        em.flush();
    }

    @Test
    @DisplayName("[success] em.persist 는 bulk 처리를 해주는가?")
    @Transactional
    public void bulk(){
        // given ? 어캐 확인하지
        for(long i = 200; i < 1000; i++){
            CategoryData categoryData = new CategoryData(i, "", "", "", "");
            em.persist(categoryData);
        }
        // when
        em.flush();
        em.find(CategoryData.class, 1L);
        // then
    }

}
