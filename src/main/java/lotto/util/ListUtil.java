package lotto.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ListUtil {
    public static List<Integer> createShuffledNumbers(int lowerBoundary, int upperBoundary) {
        List<Integer> numbers = new ArrayList<>();
        IntStream.rangeClosed(lowerBoundary, upperBoundary)
                        .forEach(numbers::add);
        Collections.shuffle(numbers);

        return numbers;
    }

    public static List<Integer> getFrontSubListAndSort(List<Integer> integerList, int length) {
        List<Integer> frontSubList = integerList.subList(0, length);
        Collections.sort(frontSubList);
        return frontSubList;
    }
}
