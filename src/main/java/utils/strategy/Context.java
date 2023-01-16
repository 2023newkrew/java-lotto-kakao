/**
 * try-catch context
 */
package utils.strategy;

import view.View;

public class Context<T> {
    private ParseStrategy<T> parse;
    private RecurseStrategy<T> recurse;

    public Context(ParseStrategy<T> parse, RecurseStrategy<T> recurse) {
        this.parse = parse;
        this.recurse = recurse;
    }

    public T execute(String input) {
        try {
            return parse.call(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return recurse.call();
        } catch (RuntimeException e) {
            e.printStackTrace();
            (new View()).printUnknownErrorMessage();
            return recurse.call();
        }
    }
}
