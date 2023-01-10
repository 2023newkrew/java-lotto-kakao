package lotto.view;

import lotto.domain.LottoTickets;
import lotto.utils.StringConversion;

import java.util.Scanner;

import static lotto.utils.Constants.MIN_PURCHASE_PRICE;
import static lotto.utils.LottoMessage.*;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int inputManualCount(int amount){
        System.out.println(INPUT_MANUAL_COUNT.getMessage());
        int manualCount = Integer.parseInt(scanner.nextLine());
        if (manualCount*MIN_PURCHASE_PRICE > amount) {
            throw new IllegalArgumentException("수동으로 구매하는 수가 총 로또의 수보다 많습니다.");
        }
        return manualCount;
    }

    public int inputUserAmount(){
        System.out.println(INPUT_AMOUNT.getMessage());
        return Integer.parseInt(scanner.nextLine());
    }

    public String inputWinNumber(){
        System.out.println(INPUT_WIN_NUMBERS.getMessage());
        return scanner.nextLine();
    }

    public int inputBonusNumber(){
        System.out.println(INPUT_BONUS_BALL.getMessage());
        return Integer.parseInt(scanner.nextLine());
    }

    public LottoTickets inputManualNumbers(int totalAmount, int manualCount) {
        LottoTickets lottoTickets = new LottoTickets(totalAmount);
        if (manualCount != 0) {
            System.out.println(INPUT_MANUAL_NUMBERS.getMessage());
            StringConversion stringConversion = new StringConversion();
            for (int i=0; i< manualCount; i++) {
                String userNumber = scanner.nextLine();
                lottoTickets.registerManualTicket(stringConversion.changeToLottoTicket(userNumber));
            }
        }
        return lottoTickets;
    }
}
