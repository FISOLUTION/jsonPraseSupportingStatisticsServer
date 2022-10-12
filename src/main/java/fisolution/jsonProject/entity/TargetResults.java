package fisolution.jsonProject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TargetResults {

    @Id @GeneratedValue
    private Long id;

    private String originalFile;

    /**
     * 구문 정확성 오류 필드
     */
    private DataStatus G011;    // 항목 및 Depth
    private DataStatus G022;    // 데이터 타입
    private DataStatus G031;    // 필수 여부
    private DataStatus G041;    // 유효값
    private DataStatus G051;    // 하위 원소 갯수
    private DataStatus G052;    // 문자열 길이
    private DataStatus G053;    // 숫자 범위
    private DataStatus G061;    // 패턴
    private DataStatus G071;    // 추가항목 허용여부
    private DataStatus G081;    // 유일성 여부
    private DataStatus G091;    // 소숫점 자리수
    private DataStatus G101;    // Null 허용

    /**
     *  의미 정확성 오류 필드
     */

    private DataStatus M011;    // 클래스 정합성
    private DataStatus M021;    // id 일치성
    private DataStatus M031;    // 제외 처리 준수

    @OneToOne(mappedBy = "result")
    private TargetDataSet targetDataSet;
}
