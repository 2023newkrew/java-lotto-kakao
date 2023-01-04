# java-lotto-kakao

## 기능 요구 사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 현재 로또 생성기는 자동 생성 기능만 제공한다. 
- 사용자가 수동으로 추첨 번호를 입력할 수 있도록 해야 한다.
- 입력한 금액, 자동 생성 숫자, 수동 생성 번호를 입력하도록 해야 한다.
- 로또 1장의 가격은 1000원이다.


---

## 기능 목록

### 실핼 결과
![Screen Shot 2023-01-04 at 4.07.10 PM.png](docs%2FScreen%20Shot%202023-01-04%20at%204.07.10%20PM.png)

### Class Diagram
![class_diagram.png](docs%2Fclass_diagram.png)

### View

#### inputView

- [ ] 구입금액을 입력받는 기능
  - [ ] 1000보다 낮으면 IllegalArgumentException이 발생한다.
  - [ ] 1000으로 안나누어 떨어지면 IllegalArgumentException이 발생한다.
- [ ] 수동으로 구매할 로또 수를 입력 받는 기능
- [ ] 당첨 번호를 입력하는 기능
- [ ] 보너스 볼을 입력받는 기능

#### outputView

- [ ] input에 대한 출력 기능
- [ ] 전체 로또를 출력하는 기능(수동 우선, 이후 자동)
- [ ] 결과를 출력하는 기능
  - [ ] 등수별 결과 출력
  - [ ] 수익률 출력

### Controller

#### LottoController
- [ ] 로또 게임 실행 및 흐름 제어


### Domain

#### LottoHandler
- [ ] Lottos를 생성한다.
- [ ] Lottos의 결과를 얻는다

#### Lottos
- [ ] List\<Lotto\>를 가진 일급 객체이다.
- [ ] List\<Lotto\>의 등수를 매기는 기능이 존재한다.

#### Lotto
- [ ] 1 ~ 45의 숫자를 중복 없이 가진다.
  - [ ] 1 ~ 45 인지 확인한다.
  - [ ] 중복을 확인한다.
- [ ] 수동 생성 기능이 있다.
- [ ] 자동 생성 기능이 있다.
- [ ] 당첨 번호와 비교 기능이 있다.
- [ ] 출력 포맷을 제공한다.

#### LottoNumbers
- [ ] List\<LottoNumber\>를 가진 일급 객체이다.
- [ ] 중복된 LottoNumber를 판별하는 기능을 가진다.

#### LottoNumber
- [ ] 1 ~ 45 숫자를 가진다.

#### LottoAnswer
- [ ] LottoNumbers와 BonusBall을 가진 객체이다.

#### BonusBall
- [ ] 2등 판별을 위한 BonusBall 객체이다




