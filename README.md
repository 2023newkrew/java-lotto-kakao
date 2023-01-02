# java-lotto-kakao

## 요구사항

### 기능

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

```text
구입금액을 입력해 주세요.
14000
14개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6
보너스 볼을 입력해 주세요.
7

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
5개 일치, 보너스 볼 일치(30000000원) - 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
```

### 컨벤션

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

## 설계 (Draft)

### Domain

- `Lotto`
    - `LottoNumbers lottoNumbers`
    - `SingleLottoNumber bonusNumber`
    - `isValidLotto()`

- `SingleLottoNumber`

- `LottoNumbers`
    - `List<SingleLottoNumber> nums`

- `UserLotto`
    - `LottoNumbers lottoNumbers`

- `UserLottos`
    - `List<UserLotto> lottos`
    - `Money userMoney`
    - `ResultMap resultMap`

- `ResultMap`
    - `HashMap<prize, amount> resultMap`

- `Money`
    - `getLottoAmount()`
    - `isValidLottoMoney()`

- `LottoStatistics`
    - `Money totalInputMoney`
    - `ResultMap resultMap`
    - `calculateProfit()`

### View

- `InputView`
- `OutputView`

### Controller

- `LottoController`

### Utils

- `RandomNumberGenerator`

## 기능 명세

### MoneyTest

- [x] 구매금액은 1000원 단위여야 한다
- [x] 구매금액이 1000원 단위가 아니면 예외가 발생한다
- [x] 구매금액이 양수가 아니면 예외가 발생한다
- [x] 구매금액만큼 살 수 있는 로또의 개수를 반환한다

### SingleLottoNumberTest

- [x] 로또 번호는 1에서 45 사이여야 한다
- [x] 로또 번호는 1에서 45 사이가 아니면 예외가 발생한다

### LottoNumbersTest

- [x] 입력되는 로또 번호가 6개여야 한다
- [x] 입력되는 로또 번호가 6개가 아니면 예외가 발생한다
- [x] 중복된 로또 번호가 있으면 예외가 발생한다
- [x] 보너스 번호를 받아서 포함되어 있으면 true를 반환한다
- [x] 보너스 번호를 받아서 포함되어 있지 않으면 false를 반환한다
- [x] 사용자의 로또 번호들을 받아서 일치하는 번호의 개수를 반환한다
