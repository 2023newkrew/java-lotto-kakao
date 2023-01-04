- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

## 기능 목록

- 로또 금액 입력받기
- 금액만큼의 로또 발급
- 로또 번호 자동 생성
    - 45까지
- 당첨 번호 입력
- 보너스 볼 입력
- 당첨 통계 구하기
- 수익률 계산하기

## 모델링

- Lotto
- Buyer
- LottoResult
- View
- Parser

## 1.3

- Buyer budget 일급객체로
- Buyer lotteries 일급컬렉션으로
- Lottery numbers 일급컬렉션으로
- LotteryResult winningNumbers 일급컬렉션으로
- Lottery의 numberCollection을 일급 컬렉션으로
- Rank에 index 없애기
- Lottery Match에서 bonusNumber 분리
- 수익률!

---

## 1차 리뷰

- 중복 검사 로직 말고 다른 Collection 고려
- autoGenerator의 역할 분리
- 객체 메시지 전달로 로직 개선
- SRP 고려하여 리팩토링

---

## step2 리팩토링 사항 및 기능목록

객체 역할과 SRP 고려

- buyerProfit 테스트용 생성자 제거
- buyerProfit 임계치 값 처리
- 로또 자동 생성 객체 별도 유지 SRP 고려
- Buyer getBuyerResult 리팩토링
- 수동 로또 구매
- 수동 로또 번호 입력
- 사용자 입력 값에 대한 예외처리