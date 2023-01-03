# java-lotto-kakao
---

## 도메인
#### GeneralNumber (숫자 클래스)
  - 검증(GeneralNumberValidator.validate())
    - 최소 최대값 범위 확인(1~45)
    - 숫자 형식이 맞는지 확인
#### BonusNumber (보너스 숫자) extends GeneralNumber
  - GeneralNumber 클래스 상속
  - 당첨 번호와 다른 숫자
#### Lotto (로또 번호 숫자 리스트)
  - List<Integer> numbers를 멤버 변수로 갖는 __일급 컬렉션__
  - [1, ... , 45] 변수로 가지고 있음
  - 생성자에서 Collections.shuffle() -> 앞의 6개 뽑아서 저장
  - getResult(WinningLotto, BonusNumber)
    - 알맞은 Result를 계산하는 로직
    - contains 합
    - 보너스볼 일치여부
    - Result Enum 타입 마다 테스트 구현
  - IsBonus(BonusNumber)
    - 보너스 숫자와 일치하는 숫자가 있는지 확인
  - getLottoNumbers()
    - 로또 숫자들을 toString() 메서드를 통해 리스트 형식 문자열로 반환
#### WinningLotto (당첨 번호 숫자 리스트 일급 컬렉션) extends Lotto
  - 사용자에게 입력되는 당첨 로또 번호
  - 검증(WinningLottoValidator)
    - 사이즈 6개
    - 모두 다른 숫자

#### Lottos (List<Lotto>)
  - List<Lotto> lottos를 멤버 변수로 갖는 __일급 컬렉션__ 
  - 생성자(count) -> List<Lotto> 생성
  - getTotalReslut(WinningLotto, Bonus)
    - 당첨 결과확인 기능
    - TotalResult 반환
  - getPurchasedLottosNumbers()
    - 구매한 로또 번호들을 List<String>로 반환하는 메서드
#### TotalResult (일치 결과)
  - Map<Result, Integer> 를 갖는 __일급 컬렉션__
  - getValueOfResult
    - Result 값을 key로 하여 value를 얻는다.
  - getWinningsOfResult
    - Result 에 대한 당첨금을 얻는다.
  - getProfit
    - 수익률을 얻는다.
#### Result (로또 일치 여부 Enum)
  - Result.[NONE, THREE, FOUR, FIVE, FIVEBONUS, SIX]
  - int winnings, String description을 멤버 변수로 갖는다.
#### Constants (공통 상수 모음)
  - 로또 범위 최소/최대, 로또 번호 길이, 구분자 등

---

## 입출력
#### InputView
- 구입 금액 입력 (In)
- 당첨 번호 입력 (In)
- 보너스볼 번호 입력 (In)
#### OutputView
- 구입 금액 입력 요구 (Out)
- 로또 개수 출력 (Out)
- 로또 번호 목록 출력 (Out)
- 당첨 번호 입력 요구 (Out)
- 보너스볼 번호 입력 요구 (Out)
- 번호 일치 여부 출력 (Out)
- 수익률 출력 (Out)
---

## 컨트롤러

- LottoMain (Controller)
  - 구입 금액 입력 요구 (Out)
  - 구입 금액 입력 (In)
  - 로또 개수 출력 (Out)
  - 구입 금액에 맞는 로또 번호 목록 생성 (Lottos)
  - 로또 번호 목록 출력 (Out)
  - 당첨 번호 입력 요구 (Out)
  - 당첨 번호 입력 (In)
  - WinningNumbers 생성 (WinningNumbers)
  - 보너스볼 번호 입력 요구 (Out)
  - 보너스볼 번호 입력 (In)
  - Bonus 생성 (Bonus)
  - 번호 일치 여부 판단 (Check)
  - 번호 일치 여부 출력 (Out)
  - 수익률 계산 (Profit)
  - 수익률 출력 (Out)


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


---
## 질문
1. 일급컬렉션에 static 변수가 있어도 되는가?
   - Lotto 클래스 내에 1부터 45까지의 정수를 갖는 static 변수를 두어, 이를 shuffle 한 뒤 6자리를 뽑아 사용하려 하였습니다.(현재는 생성자 내에 매번 생성. 이 또한 바람직하지 않아 수정 예정입니다..)
   - Lotto 클래스는 List<Integer> numbers를 위한 일급 컬렉션인데, 스태틱 변수 추가가 일급 컬렉션에 본질을 해치지 않는다고 생각되는데 맞는 생각일까요..?
2. Map 변수 이름을 짓는 방식이 있을까요?
3. validate 기능 메서드를 따로 분리하여 다루다보니 중복되는 로직이 많이 발생합니다.
   - ex) parseInt() 중복, split 중복
4. 메서드 파라미터에 final을 쓰는 것이 좋은가?
5. 예외 관련 테스트 메서드에서 특정 예외가 발생하는 것을 확인하는 기능은 제공되지만, 특정 예외만이 발생하지 않는 경우를 테스트 하는 기능은 제공되지 않습니다. 혹시 어떤 방법이 많이 이용되나요?
