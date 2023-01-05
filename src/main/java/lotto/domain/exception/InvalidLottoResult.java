package lotto.domain.exception;

/**
 * Thrown when LottoResult gets impossible pair of matchCount and matchBonus.<br>
 * For example, (matchCount, matchBonus) = (-1, true) is impossible.
 */
public class InvalidLottoResult extends RuntimeException {
}
