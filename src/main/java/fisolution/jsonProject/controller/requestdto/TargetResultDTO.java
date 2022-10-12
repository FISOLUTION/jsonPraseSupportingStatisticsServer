package fisolution.jsonProject.controller.requestdto;

import fisolution.jsonProject.entity.DataStatus;
import fisolution.jsonProject.entity.TargetResults;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TargetResultDTO {

    private String originalFile;

    /**
     * 구문 정확성 오류 필드
     */
    private DataStatus g011;    // 항목 및 Depth
    private DataStatus g022;    // 데이터 타입
    private DataStatus g031;    // 필수 여부
    private DataStatus g041;    // 유효값
    private DataStatus g051;    // 하위 원소 갯수
    private DataStatus g052;    // 문자열 길이
    private DataStatus g053;    // 숫자 범위
    private DataStatus g061;    // 패턴
    private DataStatus g071;    // 추가항목 허용여부
    private DataStatus g081;    // 유일성 여부
    private DataStatus g091;    // 소숫점 자리수
    private DataStatus g101;    // Null 허용

    /**
     *  의미 정확성 오류 필드
     */

    private DataStatus m011;    // 클래스 정합성
    private DataStatus m021;    // id 일치성
    private DataStatus m031;    // 제외 처리 준수

    public TargetResults toEntity(){
        return TargetResults.builder()
                .originalFile(originalFile)
                .g011(g011)
                .g022(g022)
                .g031(g031)
                .g041(g041)
                .g051(g051)
                .g052(g052)
                .g053(g053)
                .g061(g061)
                .g071(g071)
                .g081(g081)
                .g091(g091)
                .g101(g101)
                .build();
    }
}
