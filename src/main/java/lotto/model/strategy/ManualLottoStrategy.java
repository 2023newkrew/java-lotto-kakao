package lotto.model.strategy;

import lotto.model.Lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ManualLottoStrategy implements LottoIssueStrategy {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Lotto issue() {
        return Lotto.of(inputManualLotto());
    }

    public List<Integer> inputManualLotto() {
        return toList(scanner.nextLine());
    }

    private List<Integer> toList(String inputNumbers) {
        String[] slicedNumbers = inputNumbers.replaceAll(" ", "").split(",");
        return Arrays.stream(slicedNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList())
                ;
    }
}
