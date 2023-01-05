package lotto.config;

import lotto.domain.GeneratePolicy;
import lotto.domain.RandomGeneratePolicy;

public class AppConfig {

    private AppConfig() {
    }

    private static final GeneratePolicy generatePolicy = new RandomGeneratePolicy();

    public static GeneratePolicy getGeneratePolicy() {
        return generatePolicy;
    }
}
