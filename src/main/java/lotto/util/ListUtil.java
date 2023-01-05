package lotto.util;

import java.util.Collections;
import java.util.List;

public class ListUtil {
    public static List<Integer> shuffleNumbers(List<Integer> numbers) {
        Collections.shuffle(numbers);
        return numbers;
    }

    public static List<Integer> getFrontSubListAndSort(List<Integer> integerList, int length) {
        List<Integer> frontSubList = integerList.subList(0, length);
        Collections.sort(frontSubList);
        return frontSubList;
    }
}
