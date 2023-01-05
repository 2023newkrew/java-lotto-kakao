package lotto.domain.exception;

/**
 * Thrown when {@code ONE_TRIAL_BALL_COUNT(=6)} balls of LottoTrial are not distinct,
 * or bonus ball is same as one of 6 numbers of WinNumber.
 */
public class DuplicatedBallNumber extends RuntimeException {
}
