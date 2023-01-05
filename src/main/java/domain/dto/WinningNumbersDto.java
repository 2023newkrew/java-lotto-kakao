package domain.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WinningNumbersDto {
    private List<Integer> lottoNumbers;
    private Integer bonusNumber;

    public WinningNumbersDto(List<Integer> lottoNumbers, int bonusNumber) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }

    @FunctionalInterface
    public static interface NumberGeneratable {
        List<Integer> generate();
    }

    public static class RandomNumberGenerator implements NumberGeneratable {
        private final int MIN_LOTTO_NUMBER = 1;
        private final int MAX_LOTTO_NUMBER = 45;
        private final int LOTTO_NUMBER_SIZE = 6;

        private List<Integer> lottoNumberPool = new ArrayList<>(
                IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER+1).
                        boxed()
                        .collect(Collectors.toList()));

        @Override
        public List<Integer> generate() {
            Collections.shuffle(lottoNumberPool);
            return lottoNumberPool.subList(0, LOTTO_NUMBER_SIZE);
        }
    }
}
