package view;

import buyer.BuyerResult;
import lotto.Lottery;
import lotto.Rank;

import java.util.Arrays;
import java.util.List;

public class OutputView {
    public void printLotteries(List<Lottery> lotteries){
        lotteries.forEach(System.out::println);
    }

    public void printResult(BuyerResult result){
        Arrays.stream(Rank.values()).forEach((e)->{
            if(e != Rank.NONE)
                System.out.println(e.match.getMatchCount()+"개 일치"+"("+e.prize+")- "+result.getResult().get(e.index)+"개");
        });
    }
}
