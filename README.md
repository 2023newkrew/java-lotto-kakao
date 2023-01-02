# java-lotto-1

### 기능 요구사항
* 로또 발권
  * 구입금액 입력
  * 금액에 맞는 로또 매수 확인
  * 해당 매수에 맞는 로또 발급
    * 1에서 45의 숫자중 중복되지 않고 무작위 6개의 숫자를 담은 정렬된 배열 생성


* 로또 당첨 확인
  * 당첨 번호 입력
  * 보너스 볼 입력
  * 발급된 로또와 당첨 번호의 일치도 확인


* 당첨 통계
  * 당첨 금액 계산
  * 수익률 계산

### 구현할 기능 목록

* 콘솔 로직
* 도메인 로직
  * Issuer (로또 발권)
    * [ ] 1에서 45의 숫자중 중복되지 않고 무작위 6개의 숫자를 담은 정렬된 배열 생성
    * [ ] 입력받은 로또 매수만큼 상기 행동 반복
  * DrawChecker (추첨 확인)
    * [ ] 입력받은 추첨 결과에 따라 당첨 결과 확인
    * [ ] 발권된 로또 매수만큼 상기 행동 반복
  * StatTracker (통계 확인)
    * [ ] 당첨 결과에 따른 통계 작성