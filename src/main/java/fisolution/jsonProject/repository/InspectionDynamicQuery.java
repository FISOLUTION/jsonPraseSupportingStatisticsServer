package fisolution.jsonProject.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fisolution.jsonProject.controller.requestdto.SearchInspectionRequestDTO;
import fisolution.jsonProject.entity.Inspection;
import fisolution.jsonProject.entity.QInspection;
import fisolution.jsonProject.repository.querymethod.InspectionQueryMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static fisolution.jsonProject.entity.QInspection.*;

@Repository
@RequiredArgsConstructor
public class InspectionDynamicQuery extends InspectionQueryMethod {

    private final JPAQueryFactory queryFactory;


    public Page<Inspection> search(SearchInspectionRequestDTO request, Pageable pageable) {
        List<Inspection> result = queryFactory.select(new QInspection(inspection))
                .distinct()
                .from(inspection)
                .where(
                        dataSetNameEq(request.getDataSetName()),
                        imageFileNameContains(request.getImageFileName())
                )
                .leftJoin(inspection.annotationList)
                .fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(inspection.count())
                .from(inspection)
                .fetchOne();

        return new PageImpl<>(result, pageable, total);
    }
}
