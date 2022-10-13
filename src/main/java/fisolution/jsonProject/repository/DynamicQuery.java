package fisolution.jsonProject.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import fisolution.jsonProject.controller.requestdto.SearchRequestDTO;
import fisolution.jsonProject.controller.responsedto.QTargetDataSearchResponseDTO;
import fisolution.jsonProject.controller.responsedto.TargetDataSearchResponseDTO;
import fisolution.jsonProject.repository.querymethod.TargetDataQueryMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static fisolution.jsonProject.entity.QTargetData.targetData;

@Repository
@RequiredArgsConstructor
public class DynamicQuery extends TargetDataQueryMethod {

    private final JPAQueryFactory queryFactory;

    public Page<TargetDataSearchResponseDTO> mainSearch(SearchRequestDTO dto, Pageable pageable){
        List<OrderSpecifier> orderSpecifiers = analyzeSort(pageable);

        List<TargetDataSearchResponseDTO> result = queryFactory.select(new QTargetDataSearchResponseDTO(
                        targetData.dataSetName, targetData.imageId, targetData.fileName, targetData.objectName, targetData.status, targetData.updatedDate
                ))
                .from(targetData)
                .where(
                        dataSetNameEq(dto.getDataSetName()),
                        objectNameLike(dto.getObjectName()),
                        imageIdLike(dto.getImageId()),
                        dataStatusEq(dto.getDataStatus())
                )
                .orderBy(orderSpecifiers.toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(targetData.count())
                .from(targetData)
                .fetchOne();

        return new PageImpl<>(result, pageable, total);
    }

    /**
     * QueryDSL 을 위한 Sort parsing method
     */
    private List<OrderSpecifier> analyzeSort(Pageable pageable){
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();

        if(pageable.getSort().isEmpty()){
            orderSpecifiers.add(new OrderSpecifier<>(Order.ASC, targetData.imageId));
            orderSpecifiers.add(new OrderSpecifier<>(Order.ASC, targetData.updatedDate));
        }
        else {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()) {
                    case "imageId":
                        OrderSpecifier<?> orderId = getSortedColumn(direction, targetData.imageId, "imageId");
                        orderSpecifiers.add(orderId);
                        break;
                    case "objectName":
                        OrderSpecifier<?> orderScore = getSortedColumn(direction, targetData.objectName, "objectName");
                        orderSpecifiers.add(orderScore);
                        break;
                    case "dataSetName":
                        OrderSpecifier<?> orderName = getSortedColumn(direction, targetData.dataSetName, "dataSetName");
                        orderSpecifiers.add(orderName);
                        break;
                    default:
                        break;
                }

            }
        }
        return orderSpecifiers;
    }

    public static OrderSpecifier<?> getSortedColumn(Order order, Path<?> parent, String fieldName) {
        Path<Object> fieldPath = Expressions.path(Object.class, parent, fieldName);
        return new OrderSpecifier(order, fieldPath);
    }

}
