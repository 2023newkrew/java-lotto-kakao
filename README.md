# java-lotto-kakao


## lotto.domain.constants
로또 domain에서 사용될 모든 상수들을 여기에 선언하여 사용하였습니다.

상수들에 대한 자세한 사항은 javadoc으로 남겨두었습니다.

## lotto.domain.exception
로또 domain에서 사용될 예외들을 여기에 모아놓았습니다.

예외들에 대한 자세한 사항은 javadoc으로 남겨두었습니다.

## Cash
금액을 표현하는 클래스입니다.

불변 객체이며, 금액이 음수인 경우 InvalidCacheValue 예외를 던집니다.

## LottoBallNumber
로또에 사용되는 볼 넘버(`BALLNUMBER_MIN_VALUE(=1)`~`BALLNUMBER_MAX_VALUE(=45)`)를 표현하는 클래스입니다.

플라이웨이트 패턴(정적 팩토리 메서드 및 캐싱)으로 구현하였으며 1~45 사이의 값이 아닌 경우 `InvalidLottoBallNumber` 예외를 던집니다.

## LottoCount
로또 개수를 표현하는 클래스입니다.

불변 객체이며, 개수가 음수인 경우 `InvalidLottoCountValue`를 던집니다.

## LottoPicker 및 LottoPickerRandom
`LottoPicker`는 `LottoBallNumber`를 뽑는 인터페이스이며, `pickOne()` 구현을 요구합니다.

`LottoPickerRandom`는 `LottoPicker`의 구현체이며, 랜덤한 수를 뽑고 그 수를 제거하는 로직을 가지고 있습니다.

## LottoTrial 및 LottoTrials
`LottoTrial`는 한 번의 로또 시도(6개의 서로 다른 로또 넘버)를 표현하는 클래스입니다.

빌더로 로또 넘버 하나 혹은 여러 개를 add할 수 있으며, 빌드 시 `LottoTrial` 객체를 반환합니다.

로또 넘버의 개수가 `ONE_TRIAL_BALL_COUNT(=6)`이 아닌 경우 `InvalidLottoTrial` 예외를 발생시킵니다.

`LottoTrials`는 여러 개의 `LottoTrial`을 가지는 클래스이며, 이터레이터를 통한 순회를 제공합니다.

## WinNumber
로또 당첨 번호를 가지는 Class입니다. `ONE_TRIAL_BALL_COUNT(=6)`개의 공을 가지는 `LottoTrial`과 보너스 볼 하나로 이루어집니다.

보너스 볼이 다른 공과 다른 경우 `DuplicatedBallNumber` 예외를 던집니다.

또한 `LottoTrial`을 받아 `LottoResult` 타입으로 결과를 리턴하는 기능 또한 존재합니다.

## LottoResult 및 LottoResults
`LottoResult`는 당첨 번호가 정해졌을 때 로또 시도에 대한 결과를 나타내는 클래스이며, 
`0`~`ONE_TRIAL_BALL_COUNT(=6)`개 맞춤 + 보너스 맞춤 여부로 결정됩니다.

이 때 가능하지 않은 조합인 경우 `InvalidLottoResult` 예외를 던집니다.

## WinningCondition
로또 결과들, 상금, 프린트 순서 세 가지 속성으로 구현되어 있는 enum 구현체이다.

## TotalResult
LottoResult를 받아 WinningCondition 정보를 참고하여 각 등수별 당첨 횟수, 수익률 등을 계산해주는 객체이다.
