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
        System.out.println("?????? ???????");
        em.persist(targetData1);
        // identity ?????? ?????? ?????? ??????

        CategoryData categoryData = new CategoryData();
        em.persist(categoryData);

        System.out.println("?????? ???????");
        CategoryData categoryData1 = new CategoryData();
        em.persist(categoryData1);
//        jdbcTemplate.execute("get next sequence");
//        jdbcTemplate.update("insert into CategoryData (CATEGORYNAME) VALUES ('ddd')");
        // ?????? ????????? ?????? ?????? ?????????. ?????? jpaRepository ??? ????????? ??? ?????? em.persist() ?????? ???????????? ????????? ?????? ???????
        // em.persist() ????????? ???????????? bulk ????????? ??????????
        em.flush();
    }

    @Test
    @DisplayName("[success] em.persist ??? bulk ????????? ?????????????")
    @Transactional
    public void bulk(){
        // given ? ?????? ????????????
        // ????????? ?????? batch size 1 -> 22 ??? ??? ???????????? insert ?????? ????????? ???, 100 -> 4??? ??????
        long l = System.currentTimeMillis();
        for(long i = 200; i < 100000; i++){
            CategoryData categoryData = new CategoryData(i, "", "", "", "");
            em.persist(categoryData);
        }
        // when
        em.flush();
        long e = System.currentTimeMillis();
        System.out.println(e - l);
        em.find(CategoryData.class, 1L);
        // then
    }

}
