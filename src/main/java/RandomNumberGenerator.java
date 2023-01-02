import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements NumberGeneratable{
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_SIZE = 6;

    @Override
    public List<Integer> generate() {
        List<Integer> list = new ArrayList<>();

        for(int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++){
            list.add(i);
        }

        Collections.shuffle(list);
        return list.subList(0,LOTTO_NUMBER_SIZE);
    }
}
