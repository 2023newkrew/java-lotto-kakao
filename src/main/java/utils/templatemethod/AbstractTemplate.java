package utils.templatemethod;

import view.View;

public abstract class AbstractTemplate<T> {
    protected T parse(String param) {
        return (T) new Object();
    }

    protected T retry() {
        return (T) new Object();
    }

    public T run(String input) {
        try {
            return parse(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return retry();
        } catch (RuntimeException e) {
            e.printStackTrace();
            (new View()).printUnknownErrorMessage();
            return retry();
        }
    }
}
