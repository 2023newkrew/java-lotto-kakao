# java-lotto-kakao
## 기능 요구사항
- [x] 로또 용지를 생성하는 기능
  - [x] 로또 번호는 숫자 중복 없음
  - [x] 로또 번호는 1~45 만 가능
  - [x] 로또 번호는 오름차순
- [x] 랜덤 숫자를 생성하는 기능
- [x] 로또 용지와 당첨 번호를 비교해 당첨을 확인하는 기능
  - [x] 당첨 번호와 보너스 번호 중복 되지 않음
- [x] 여러 장의 로또 용지를 가지는 일급 콜렉션
- [x] 여러 장의 로또 용지의 당첨 결과를 반환하는 기능
- [x] 구입금액을 입력하는 기능
- [x] 구입 개수만큼 로또 용지를 생성
- [x] 당첨 번호를 입력하는 기능 (보너스 볼 포함)
- [x] 당첨 통계를 출력하는 기능
- [x] 수익률을 계산하는 기능


## 리팩토링 목록
- [x] 상수 저장 클래스 삭제 및 관련 클래스에 정의
- [x] 불필요한 래퍼 타입 원시 타입으로 변경
- [x] LottoNumber 클래스 생성하여 로또 숫자 포장 및 유효성 검사 처리
- [x] LottoResults 불변 객체로 사용하도록 수정
- [x] LottoChecker 삭제 후 메서드들 LottoResultsType 으로 이동
- [x] 메시지만 다른 동일한 예외 타입들 테스트시 구분 가능하도록 변경
  - 예외 상황별 예외 클래스 커스텀
- [x] 역할이 모호한 LottoTicket 제거 
- [x] LottoNumberMakers의 로또 숫자 생성 기능을 LottoNumbers 내부 메서드로 변경 후 제거
- [x] 수동으로 구매할 로또 수를 입력하는 기능
  - 유효성 검사 포함
- [] 수동으로 구매할 번호를 입력하는 기능
  - 유효성 검사 포함
