package lotto.domain;

/**
 * LottoPicker is the interface for picking LottoBallNumber.
 * @implSpec you can implement the picking algorithm, such as random pick.
 * @author Daniel.tomi
 */
public interface LottoPicker {

    LottoBallNumber pickOne();
}
