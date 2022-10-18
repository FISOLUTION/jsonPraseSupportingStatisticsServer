package fisolution.jsonProject.init;

import fisolution.jsonProject.entity.Category;
import fisolution.jsonProject.entity.CategoryData;
import fisolution.jsonProject.entity.enumtype.DataStatus;
import fisolution.jsonProject.entity.TargetData;
import fisolution.jsonProject.entity.TargetResults;
import fisolution.jsonProject.repository.CategoryDataRepository;
import fisolution.jsonProject.repository.CategoryRepository;
import fisolution.jsonProject.repository.TargetDataRepository;
import fisolution.jsonProject.repository.TargetResultRepository;
import fisolution.jsonProject.repository.dto.CategoryDataExcel;
import fisolution.jsonProject.service.ExcelUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataInitializerForTest {

    private final TargetDataRepository targetDataRepository;
    private final TargetResultRepository targetResultRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryDataRepository categoryDataRepository;
    private final JdbcTemplate jdbcTemplate;

//    @PostConstruct
    private void postConstruct() throws NoSuchMethodException {
        init();
    }

    @Transactional
    public void init() throws NoSuchMethodException {

        List<CategoryData> categoryDataList = readExcel();

        int[][] ints = jdbcTemplate.batchUpdate("insert into CATEGORYDATA (ID, SUPERCATEGORYNAME, CATEGORYNAME, INOUT, KIND) " +
                        "VALUES (?, ?, ?, ?, ?)",
                categoryDataList,
                100,
                (PreparedStatement ps, CategoryData category) -> {
                    ps.setLong(1, category.getId());
                    ps.setString(2, category.getSuperCategoryName());
                    ps.setString(3, category.getCategoryName());
                    ps.setString(4, category.getInOut());
                    ps.setString(5, category.getKind());
                });

        long start = System.currentTimeMillis();

//        List<Category> categories = new ArrayList<>();

        for(int i = 1; i < 100; i++) {
            TargetResults targetResults = new TargetResults("originalFile", DataStatus.ERROR, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS, DataStatus.PASS);
            // id 값 넣으면 merge 일어나긴 하는데...
            TargetData targetData = new TargetData("imageId", "fileName",  "objectName", 3, "dataSetName", DataStatus.PASS, targetResults);
            CategoryData referenceById = categoryDataRepository.getReferenceById((long) i);
            Category category = new Category(targetData, referenceById);
            targetResultRepository.save(targetResults);
            targetDataRepository.save(targetData);
            categoryRepository.save(category);

//            categoryRepository.save(category);
//            categoryRepository.save(category1);

//            categories.add(category);
//            categories.add(category1);
        }
//
//        int[][] ints = jdbcTemplate.batchUpdate("insert into CATEGORY (SUPERCATEGORY, CATEGORY, TARGETDATAID) " +
//                        "VALUES (?, ?, ?)",
//                categories,
//                100,
//                (PreparedStatement ps, Category category) -> {
//                    ps.setString(1, category.getSuperCategory());
//                    ps.setString(2, category.getCategory());
//                    ps.setLong(3, category.getTargetData().getId());
//                });

        long end = System.currentTimeMillis();

        System.out.println("time : " + (end - start));


    }

    public List<CategoryData> readExcel() throws NoSuchMethodException {
        File file = new File("categoryData.xlsx");
        ExcelUtils excelUtils = new ExcelUtils();
        return excelUtils.excelToJson(file, CategoryDataExcel.class)
                .stream().map(categoryDataExcel -> CategoryDataExcel.createCategoryData(categoryDataExcel))
                .collect(Collectors.toList());
    }
}
