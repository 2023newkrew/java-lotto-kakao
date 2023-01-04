package lotto.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {

    private final static Scanner sc = new Scanner(System.in);

    public static long inputPurchaseCost() {
        System.out.println("구매금액을 입력해주세요.");
        long purchaseCost = sc.nextLong();
        sc.nextLine();

        if (purchaseCost <= 0) {
            throw new IllegalArgumentException("구매금액은 0보다 큰 수만 입력할 수 있습니다.");
        }

        return purchaseCost;
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
        List<Integer> winningNumbers = Stream.of(sc.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException("당첨 번호는 6개의 숫자여야 합니다.");
        }

        return winningNumbers;
    }

    public static int inputBonusBall() {
        System.out.println("보너스 볼을 입력해주세요.");
        return sc.nextInt();
    }
}
