package utils.strategy;

public interface ParseStrategy<T> {
    T call(String input);
}
