package lotto.domain;

import lotto.domain.exception.InvalidLottoTrial;
import static lotto.domain.constants.LottoConstants.*;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTrial {
    List<LottoBallNumber> lottoBallNumbers;
    private LottoTrial(Builder builder){ // 랜덤
        lottoBallNumbers = builder.ballNumbers.stream().collect(Collectors.toList());
        Collections.sort(lottoBallNumbers);
    }

    public List<LottoBallNumber> getBallNumbers() {
        return lottoBallNumbers;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i=0;i<ONE_TRIAL_BALL_COUNT-1;i++){
            stringBuilder.append(lottoBallNumbers.get(i)+", ");
        }
        return stringBuilder.append(lottoBallNumbers.get(ONE_TRIAL_BALL_COUNT-1)+"]").toString();
    }


    public static class Builder{
        private final Set<LottoBallNumber> ballNumbers = new HashSet<>();;

        public Builder addBall(LottoBallNumber ballNumber){
            ballNumbers.add(ballNumber);
            return this;
        }

        public Builder addBall(int ballNumber){
            addBall(LottoBallNumber.get(ballNumber));
            return this;
        }

        public Builder addBalls(Collection<LottoBallNumber> ballNumbers){
            this.ballNumbers.addAll(ballNumbers);
            return this;
        }
        public Builder addBalls(List<Integer> ballNumbers){
            for (int ball : ballNumbers){
                addBall(ball);
            }
            return this;
        }
        public Builder addRandomBalls(){
            addRandomBalls(new LottoPickerRandom());
            return this;
        }
        public Builder addRandomBalls(LottoPicker picker){
            for (int i=1;i<=ONE_TRIAL_BALL_COUNT;i++){
                ballNumbers.add(picker.pickOne());
            }
            return this;
        }

        private void validateLottoCount(){
            if (ballNumbers.size()!= ONE_TRIAL_BALL_COUNT){
                System.out.println(ballNumbers.size());
                throw new InvalidLottoTrial();
            }
        }
        public LottoTrial build(){
            validateLottoCount();
            return new LottoTrial(this);
        }
    }
}