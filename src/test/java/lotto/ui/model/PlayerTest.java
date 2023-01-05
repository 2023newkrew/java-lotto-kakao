package lotto.ui.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @DisplayName("돈 주고받기 테스트")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "'100,-50,100'              | 150",
            "'1,2,3'                    | 6",
            "'1000,-500,-500'           | 0",
    })
    void moneyCheck(String deltaMoneys, long resultMoney) {
        Player player = new Player();
        Arrays.stream(deltaMoneys.split(","))
                .map(Integer::parseInt)
                .forEach(v -> {
                    if (v > 0) {
                        player.giveMoney(v);
                        return;
                    }
                    player.takeMoney(-v);
                });
        assertThat(player.getOwnMoney()).isEqualTo(resultMoney);
    }

    @DisplayName("돈이 부족한 경우 확인")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "'100,-110'",
            "'100,-50,-50,-50'",
    })
    void moneyCheck(String deltaMoneys) {
        Player player = new Player();
        assertThatThrownBy(() -> {
            Arrays.stream(deltaMoneys.split(","))
                    .map(Integer::parseInt)
                    .forEach(v -> {
                        if (v > 0) {
                            player.giveMoney(v);
                            return;
                        }
                        player.takeMoney(-v);
                    });
        });
    }
}
