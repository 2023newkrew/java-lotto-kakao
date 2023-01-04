package util;

import java.util.List;

public class ListUtils {

    public static <T> boolean hasDuplicates(List<T> list) {
        long distinctCount = list.stream()
                .distinct()
                .count();
        return distinctCount < list.size();
    }

    public static <T> long countCommonElements(List<T> list1, List<T> list2) {
        return list2.stream()
                .filter(list1::contains)
                .count();
    }
}
