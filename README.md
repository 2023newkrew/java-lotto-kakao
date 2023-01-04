# java-lotto-kakao

### 기능 정의
- 로또 구매 금액을 전달하면 구매할 수 있는 로또의 장수를 반환한다.
- 구매할 로또의 장 수만큼 자동 구매할 경우 자동으로 로또를 생성해 반환한다.
- 구매한 한장의 로또 번호와 당첨 번호를 넣으면 당첨 결과를 반환한다.
- 구매한 전체 로또의 당첨 결과를 입력하면 당첨금 총액을 반환한다.
- 당첨 금액과 구매 금액을 넣으면 수익률을 반환한다.
- 보너스 번호는 당첨 번호와 중첩될 수 없다.

### 클래스 정의
- [x] Model (Domain)
    - [x] ComputerLotto (Wrapping)
        - [x] Lotto
        - [x] bonusNumber (정적 팩토리 메서드)
            - 캐싱기능
    - [x] Lottos
    - [x] Lotto
      - [x] LottoNumber
    - [x] DTO
        - [x] GameResult → 1등 ~ 5등까지 몇개씩 가지고 있는지에 대한 Count
            - Map(등수, 갯수), (2, 0), (3, 0) (4, 0), (5, 1))
    - [x] Enum
        - 1등 ~ 5등 + 꽝
            - GRADE (MATCH_COUNT, BONUS_MATCH, PRICE)
- [x] View
- [x] Controller (Application)
