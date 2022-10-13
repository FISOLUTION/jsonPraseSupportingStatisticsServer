package fisolution.jsonProject.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class BeanEtc {

    /**
     * QueryDsl 사용을 위한 jpaQueryFactory 추가 EntityManager 가 프록시 상태이기 때문에
     * em Bean 으로 주입받아도 상관 없다.
     */
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager em){
        return new JPAQueryFactory(em);
    }
}
