# java-lotto-kakao

### 기능 요구사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

### 프로그래밍 요구사항
- indent(인덴트, 들여쓰기) depth를 2단계에서 1단계로 줄여라.
  - depth의 경우 if문을 사용하는 경우 1단계의 depth가 증가한다. if문 안에 while문을 사용한다면 depth가 2단계가 된다.
- else를 사용하지 마라.
- 메소드의 크기가 최대 10라인을 넘지 않도록 구현한다.
- method가 한 가지 일만 하도록 최대한 작게 만들어라.
- 배열 대신 ArrayList를 사용한다.
- java enum을 적용해 프로그래밍을 구현한다.
- 규칙 3: 모든 원시값과 문자열을 포장한다.
- 규칙 5: 줄여쓰지 않는다(축약 금지).
- 규칙 8: 일급 콜렉션을 쓴다.

### 추가 기능 요구사항
- 현재 로또 생성기는 자동 생성 기능만 제공한다. 사용자가 수동으로 추첨 번호를 입력할 수 있도록 해야 한다.
- 입력한 금액, 자동 생성 숫자, 수동 생성 번호를 입력하도록 해야 한다.

### 추가 프로그래밍 요구사항
- 예외가 발생하는 부분에 대해 자바 Exception을 적용해 예외처리한다.
- 사용자가 입력한 값에 대한 예외 처리를 철저히 한다.

---

## 요구사항 정의서

- [x] 로또 구입 금액을 입력 받는다.
- [x] 수동으로 구매할 로또의 개수를 입력 받는다.
- [x] 자동 로또를 발급한다.
  - [x] 구입 개수 - 수동 로또 개수 만큼 자동 로또를 발급한다.
  - [x] 로또의 번호는 1이상 45 이하이다.
  - [x] 발급한 로또는 로또번호를 6개 가진다.
  - [x] 로또 번호들 끼리 중복되지 않는다.
- [x] 지난 주 당첨 번호와 보너스볼을 입력 받는다.
- [x] 로또들의 등수를 계산하여 출력한다.
- [x] 총 수익률을 계산하여 출력한다.

### 사용자 입력 예외처리 리스트
  - [x] 로또 구입 금액을 입력 받는다.
    - [x] 정수로 입력되어야 한다.
    - [x] 로또 구입 금액은 양의 정수이며, 1000으로 나누어 떨어져야 한다.
  - [x] 수동으로 구매할 로또의 개수를 입력 받는다.
    - [x] 정수로 입력되어야 한다.
    - [x] 구입 로또 개수보다 수동으로 구매할 로또 개수가 많아선 안된다.
    - [x] 수동으로 구매할 로또의 개수는 0 이상이다.
  - [x] 이하 입력되는 로또 번호들은 모두 정수이다.
  - [x] 수동으로 구매할 로또의 번호를 입력 받는다.
    - [x] 로또 번호는 1이상 45이하이다.
    - [x] 로또 번호는 6개이다.
    - [x] 로또 번호는 중복되지 않는다.
  - [x] 지난 주 당첨 번호와 보너스볼을 입력 받는다.
    - [x] 로또 번호는 1이상 45 이하이다.
    - [x] 로또 번호는 6개이다.
    - [x] 로또 번호는 중복되지 않는다.
    - [x] 보너스 번호는 1이상 45이하이다.
    - [x] 보너스 번호는 1개이며, 로또 번호와 중복되어선 안된다.