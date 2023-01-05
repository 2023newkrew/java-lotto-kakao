# java-lotto-kakao

## 기능 요구사항
 
* 구매 금액을 입력한다.
* 구매 금액만큼 로또를 무작위로 생성한다.
* 생성된 로또를 출력한다.
* 지난 주 당첨 번호 + 보너스 볼을 입력한다.
* [x] 당첨 번호와 비교해 로또의 등수를 계산한다.
* 당첨 결과를 출력한다.
* 수익률은 소수 둘째자리까지만 출력한다.

### 로또
* 로또 한장의 가격은 1000원이다.
* 1~45 사이의 중복이 없는 6개 숫자와 보너스볼로 구성된다.
* 로또 번호는 무작위로 발급된다.
* 로또 순위
  * 1등 : 당첨 번호와 6개가 일치하면 상금은 2000000000원이다.
  * 2등 : 당첨 번호와 5개가 일치하고 보너스 볼이 일치하면 상금은 30000000원이다.
  * 3등 : 당첨 번호와 5개가 일치하면 상금은 150000원이다.
  * 4등 : 당첨 번호와 4개가 일치하면 상금은 50000원이다.
  * 5등 : 당첨 번호와 3개가 일치하면 상금은 5000원이다.
* 입력 금액과 당첨금을 통해 수익율을 계산한다.
* 상금의 총합은 제한이 없다.

### 구매 금액
* 구매 금액의 가능한 수량까지 구매한다.
* 구매 금액의 상한 제한은 LONG_MAX 이다.
* 구매 금액의 하한 제한은 0원 초과이다.

1. Money 
2. LottoNumber 
4. LottoNumbers
3. LottoTicket
7. LottoWallet
  - long getPurchasableCount(Money price)
  - void withdraw(Money)
  - void addLotto(LottoTicket)
  - List<List<Integer>> getLottoNumbers()
  - void settleLotto(Money)
  - BigDecimal getProfitRate()
- LottoMachine
  - Lotto issueAutomatically()
8. LottoStore
  - void buyAutomatically(Wallet)
5. LottoRanking
6. LottoStats
- WinningNumbers
  - LottoRanking judge(Lotto)
9. LottoCompany
  - LottoStats analyze(LottoTicket)