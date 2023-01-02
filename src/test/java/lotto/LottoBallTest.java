package lotto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoBallTest {
    @Test
    void 로또볼은_1에서_45_사이_숫자여야_한다() {
        assertDoesNotThrow(() -> new LottoBall(1));
    }

    @Test
    void _1에서_45_밖의_숫자에서는_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> new LottoBall(0));
    }
}
