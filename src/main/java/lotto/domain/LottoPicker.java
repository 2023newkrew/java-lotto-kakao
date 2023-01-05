package lotto.domain;

/**
 * LottoPicker is the interface for picking LottoBallNumber.
 * @implSpec you can implement the picking algorithm, such as random pick.
 */
public interface LottoPicker {

    LottoBallNumber pickOne();
}
