package lotto.view;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static lotto.config.LottoConfig.LOTTO_PRICE;

public class LottoInputTemplate {
    private final Scanner scanner;

    public LottoInputTemplate(){
        scanner = new Scanner(System.in);
    }

    public Integer inputPurchaseAmount(){
        System.out.println("구입금액을 입력해 주세요.");
        Integer purchaseAmount = Integer.parseInt(scanner.nextLine().trim());
        validateInputPurchaseAmount(purchaseAmount);
        return purchaseAmount;
    }

    private void validateInputPurchaseAmount(Integer purchaseAmount){
        if(purchaseAmount <= 0){
            throw new LottoException(ErrorCode.INVALID_PURCHASE_AMOUNT);
        }
    }

    public Integer inputNumberOfManualLottoTicket(Integer purchaseAmount){
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        Integer numberOfManualLotto = Integer.parseInt(scanner.nextLine().trim());
        validateNumberOfManualLotto(purchaseAmount / LOTTO_PRICE, numberOfManualLotto);

        return numberOfManualLotto;
    }

    private void validateNumberOfManualLotto(Integer maxNumberOfLottoTicket, Integer numberOfManualLotto){
        if(maxNumberOfLottoTicket < numberOfManualLotto || numberOfManualLotto < 0){
            throw new LottoException(ErrorCode.INVALID_NUMBER_OF_TICKET);
        }
    }

    public List<List<Integer>> inputManualLottoNumbers(Integer numberOfManualLottoTicket){
        List<List<Integer>> ret = new ArrayList<>();

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for(int count = 0; count < numberOfManualLottoTicket; count++){
            ret.add(inputLottoNumbers());
        }

        return ret;
    }

    private List<Integer> inputLottoNumbers(){
        String input = scanner.nextLine().trim();

        return Arrays.stream(input.split(","))
                .map(num -> Integer.parseInt(num.trim()))
                .collect(Collectors.toList());
    }

    public List<Integer> inputWinningNumbers(){
        System.out.println("지난 주 당첨 번호 입력해 주세요.");
        return inputLottoNumbers();
    }

    public Integer inputBonusBall(){
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine().trim();
        validateBonusBall(input);
        return Integer.parseInt(input);
    }

    private void validateBonusBall(String input){
        try{
            Integer.parseInt(input);
        }catch (NumberFormatException numberFormatException){
            throw new LottoException(ErrorCode.INVALID_BONUS_BALL);
        }
    }
}
