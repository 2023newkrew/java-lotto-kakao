import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements NumberGeneratable{

    @Override
    public List<Integer> generate() {
        List<Integer> list = new ArrayList<>();

        for(int i = LottoMetaData.MIN_LOTTO_NUMBER; i <= LottoMetaData.MAX_LOTTO_NUMBER; i++){
            list.add(i);
        }

        Collections.shuffle(list);
        return list.subList(0, LottoMetaData.LOTTO_NUMBER_SIZE);
    }
}
