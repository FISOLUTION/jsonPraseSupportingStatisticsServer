package fisolution.jsonProject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class TargetResults {

    @Id @GeneratedValue
    private Long id;

    @Lob
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

    @OneToOne(mappedBy = "result")
    private TargetData targetData;

    @Builder
    public TargetResults(String originalFile, DataStatus g011, DataStatus g022, DataStatus g031, DataStatus g041, DataStatus g051, DataStatus g052, DataStatus g053, DataStatus g061, DataStatus g071, DataStatus g081, DataStatus g091, DataStatus g101, DataStatus m011, DataStatus m021, DataStatus m031) {
        this.originalFile = originalFile;
        this.g011 = g011;
        this.g022 = g022;
        this.g031 = g031;
        this.g041 = g041;
        this.g051 = g051;
        this.g052 = g052;
        this.g053 = g053;
        this.g061 = g061;
        this.g071 = g071;
        this.g081 = g081;
        this.g091 = g091;
        this.g101 = g101;
        this.m011 = m011;
        this.m021 = m021;
        this.m031 = m031;
    }

}
