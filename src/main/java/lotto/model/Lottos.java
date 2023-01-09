package lotto.model;

import lotto.constant.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 구매한 로또 객체들을 관리하는 클래스입니다.
 */
public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(int money, List<List<Integer>> manualLottos) {
        int numberOfManualLottos = manualLottos.size();
        int numberOfAutomatedLottos = money / Constants.PRICE_OF_LOTTO - numberOfManualLottos;

        checkNegative(numberOfAutomatedLottos);

        addManual(manualLottos);
        addAutomated(numberOfAutomatedLottos);
    }

    private void checkNegative(int numberOfAutomatedLottos){
        if (numberOfAutomatedLottos < 0){
            throw new IllegalArgumentException("로또 개수는 음수일 수 없습니다.");
        }
    }

    private void addManual(List<List<Integer>> manualLottos) {
        for (List<Integer> manualLotto : manualLottos) {
            lottos.add(new Lotto(manualLotto));
        }
    }

    private void addAutomated(int numberOfLottos) {
        for (int i = 0; i < numberOfLottos; i++) {
            lottos.add(new Lotto());
        }
    }

    public List<Lotto> getLottos() {
        List<Lotto> lottosReadOnly = new ArrayList<>(lottos);
        return Collections.unmodifiableList(lottosReadOnly);
    }

    public RankingResult getRankings(WinLotto winLotto) {
        final List<Ranking> rankings = new ArrayList<>();
        for (Lotto lotto : lottos) {
            rankings.add(lotto.checkWin(winLotto));
        }
        return new RankingResult(rankings);
    }
}
