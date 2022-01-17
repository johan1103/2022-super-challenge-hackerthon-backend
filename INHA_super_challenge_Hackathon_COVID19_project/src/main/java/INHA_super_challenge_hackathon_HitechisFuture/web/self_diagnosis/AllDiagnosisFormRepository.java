// 2021.01.17
// 현재 member는 H2 기반으로 DB 연동에 성공했다.
// 이제 자가진단서를 DB 연동할 차례인데, Test할 때 주의점
// member는 DB 연동이 되었으므로 H2를 종료하면 없어지긴하나, H2를 종료하지 않고 intellij만 종료하고 다시 컴파일하면
// 자가진단서 repository는 초기화가 된다. (Memory 기반으로 구현되었으므로)
// 따라서 테스트할 경우, 프로그램 실행시 한번에 회원가입, 로그인, 자가진단서 작성을 모두 진행해줘야한다.



package INHA_super_challenge_hackathon_HitechisFuture.web.self_diagnosis;

import java.util.Optional;

public interface AllDiagnosisFormRepository {

    // 특정인의 자가진단서 repository 조회
    // **************************************
    // 주의 : 특정인의 이름으로 조회하는 것은 안된다. 중복된 이름이 있을 수 있기 떄문이다.
    // 따라서 특정인의 이름으로 조회를 시도하면, 해당 member 객체의 고유 ID를 가져와서 그것으로 조회를 하는 로직을 설계했다.
    // **************************************
    Optional<SingleDiagnosisRepository> findById(Long id);

    // 특정인의 자가진단서 repository 생성
    void save(SingleDiagnosisRepository singleDiagnosisRepository);

    // test용
    int getSize();

}
