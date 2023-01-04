package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;
import static lotto.domain.LottoConstants.*;

public class TotalResult {
    private final Map<LottoResult, LottoCount> lottoResultCount = new HashMap<>();
    private Cash totalPrize = new Cash(0);
    private int trial = 0;

    public TotalResult(){
        for (int i = 0; i< ONE_TRIAL_BALL_COUNT; i++){
            lottoResultCount.put(LottoResult.get(i, false), new LottoCount(0));
            lottoResultCount.put(LottoResult.get(i, true), new LottoCount(0));
        }
        lottoResultCount.put(LottoResult.get(ONE_TRIAL_BALL_COUNT, false), new LottoCount(0));
    }
    public void add(LottoResult lottoResult) {
        trial++;
        addPrize(lottoResult);
        lottoResultCount.put(lottoResult, lottoResultCount.get(lottoResult).add(1));
    }

    private void addPrize(LottoResult lottoResult){
        List<Cash> prizes = Arrays.stream(WinningCondition.values())
                .map(v -> v.getPrizeIfMatch(lottoResult))
                .filter(v->v.compareTo(0L)>0)
                .collect(Collectors.toList());
        if (prizes.size()!=0){
            totalPrize = totalPrize.plus(prizes.get(0));
        }
    }

    public LottoCount getLottoCountOfResult(LottoResult lottoResult){
        return lottoResultCount.get(lottoResult);
    }

    public LottoCount getLottoCountOfResults(LottoResults lottoResults){
        LottoCount result = new LottoCount(0);
        for (Iterator<LottoResult> it = lottoResults.getIterator(); it.hasNext(); ) {
            result = result.add(getLottoCountOfResult(it.next()));
        }
        return result;
    }

    public double getSurplusRatio() throws ArithmeticException{
        if (trial == 0){
            throw new ArithmeticException();
        }
        return (double)(totalPrize.getCash()) / (trial*LOTTO_PRICE);
    }


    @Override
    public String toString(){
        String startString = "당첨 통계\n-----------\n";
        return startString + statisticsString() + surplusRatioString();
    }


    private String statisticsString(){
        String[] strings = new String[WinningCondition.values().length];
        for (WinningCondition con : WinningCondition.values()){
            strings[con.getOrder()] = con + "- " +
                    getLottoCountOfResults(con.getLottoResults());
        }
        return String.join("\n", strings)+"\n";
    }

    private String surplusRatioString(){
        double surplusRatio = getSurplusRatio();
        return String.format("총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)\n",
                surplusRatio, (surplusRatio>1 ? "이득이":"손해"));
    }
}
