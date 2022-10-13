package fisolution.jsonProject.controller.requestdto;

import fisolution.jsonProject.entity.DataStatus;
import fisolution.jsonProject.entity.TargetResults;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class TargetResultDTO {
    @NotNull
    private String originalFile;

    /**
     * 구문 정확성 오류 필드
     */
    @NotNull
    private DataStatus g011;    // 항목 및 Depth
    @NotNull
    private DataStatus g022;    // 데이터 타입
    @NotNull
    private DataStatus g031;    // 필수 여부
    @NotNull
    private DataStatus g041;    // 유효값
    @NotNull
    private DataStatus g051;    // 하위 원소 갯수
    @NotNull
    private DataStatus g052;    // 문자열 길이
    @NotNull
    private DataStatus g053;    // 숫자 범위
    @NotNull
    private DataStatus g061;    // 패턴
    @NotNull
    private DataStatus g071;    // 추가항목 허용여부
    @NotNull
    private DataStatus g081;    // 유일성 여부
    @NotNull
    private DataStatus g091;    // 소숫점 자리수
    @NotNull
    private DataStatus g101;    // Null 허용

    /**
     *  의미 정확성 오류 필드
     */
    @NotNull
    private DataStatus m011;    // 클래스 정합성
    @NotNull
    private DataStatus m021;    // id 일치성
    @NotNull
    private DataStatus m031;    // 제외 처리 준수

    public TargetResultDTO(TargetResults result) {
        originalFile = result.getOriginalFile();
        g011 = result.getG011();
        g022 = result.getG022();
        g031 = result.getG031();
        g041 = result.getG041();
        g051 = result.getG051();
        g052 = result.getG052();
        g053 = result.getG053();
        g061 = result.getG061();
        g071 = result.getG071();
        g081 = result.getG081();
        g091 = result.getG091();
        g101 = result.getG101();
        m011 = result.getM011();
        m021 = result.getM021();
        m031 = result.getM031();
    }

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
                .m011(m011)
                .m021(m021)
                .m031(m031)
                .build();
    }
}
