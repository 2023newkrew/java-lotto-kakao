package utils;

public interface ParseStrategy<T> {
    T call(String input);
}
