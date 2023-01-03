import lotto.controller.LottoController;

public class LottoSimulationApplication {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        while(true) {
            try {
                lottoController.startLottoGame();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
