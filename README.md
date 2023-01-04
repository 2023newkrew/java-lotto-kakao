# java-lotto-kakao

---
## 요구사항 정의서

- [x] 로또 구입 금액을 입력 받는다.
- [x] 로또 번호를 발급한다.
  - [x] 발급한 로또 번호는 6개이다.
  - [x] 로또 번호는 1이상 45이하이다. 
  - [x] 로또 번호는 중복되지 않는다.
- [x] 로또 구입 금액에 해당하는 로또를 발급한다.(로또 1장의 가격은 1000원)
  - [x] 수동으로 발급한 로또의 갯수를 제외하고 발급한다.
- [x] 지난 주 당첨 번호를 입력 받는다.
- [x] 보너스 볼을 입력 받는다.
- [x] 로또 번호가 몇 개 일치하는지 계산한다.
- [x] 로또의 당첨금액을 계산한다.
- [x] 총 수익률을 계산하여 출력한다.
- [x] 로또 번호를 수동으로 입력받는다.
- [x] 수동으로 입력 받은 번호로 로또를 발급 받는다.
- [ ] 사용자의 입력값에 대한 예외 처리를 한다.
  - [ ] 입력한 구입 금액이 양의 정수가 아닌 경우 런타임 에러가 발생한다.
  - [ ] 입력한 당첨 번호가 ,로 연결된 양의 정수가 아니면 런타임 에러가 발생한다.
  - [ ] 입력한 당첨 번호가 6개가 아니면 런타임 에러가 발생한다.
  - [ ] 입력한 보너스 볼 숫자가 양의 정수가 아닌 경우 런타임 에러가 발생한다.
  - [ ] 입력한 수동으로 구매할 로또 수가 양의 정수가 아닌 경우 런타임 에러가 발생한다.
  - [ ] 입력한 수동으로 구매할 로또 번호가 ,로 연결된 양의 정수가 아니면 런타임 에러가 발생한다.
  - [ ] 입력한 수동으로 구매할 로또 번호가 6개가 아니면 런타임 에러가 발생한다.