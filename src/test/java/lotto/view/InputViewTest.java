package lotto.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InputViewTest {
    @Test
    void validateIsInteger_정수인_경우_예외가_발생하지_않아야_함() throws NoSuchMethodException {
        InputView inputView = InputView.getInstance();

        Method validateIsIntegerMethod = InputView.class.getDeclaredMethod("validateIsInteger", String.class);
        validateIsIntegerMethod.setAccessible(true);

        assertThatCode(() -> {
            validateIsIntegerMethod.invoke(inputView, "0");
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0.01", "a"})
    void validateIsInteger_정수가_아닌_경우_예외가_발생해야_함(String input) throws NoSuchMethodException {
        InputView inputView = InputView.getInstance();

        Method validateIsIntegerMethod = InputView.class.getDeclaredMethod("validateIsInteger", String.class);
        validateIsIntegerMethod.setAccessible(true);

        assertThatThrownBy(() -> {
            validateIsIntegerMethod.invoke(inputView, input);
        });
    }

    @Test
    void validateIsNotNegative_음수가_아닌_경우_예외가_발생하지_않아야_함() throws NoSuchMethodException {
        InputView inputView = InputView.getInstance();

        Method validateIsNotNegativeMethod = InputView.class.getDeclaredMethod("validateIsNotNegative", String.class);
        validateIsNotNegativeMethod.setAccessible(true);

        assertThatCode(() -> {
            validateIsNotNegativeMethod.invoke(inputView, "0");
        }).doesNotThrowAnyException();
    }

    @Test
    void validateIsNotNegative_음수인_경우_예외가_발생해야_함() throws NoSuchMethodException {
        InputView inputView = InputView.getInstance();

        Method validateIsNotNegativeMethod = InputView.class.getDeclaredMethod("validateIsNotNegative", String.class);
        validateIsNotNegativeMethod.setAccessible(true);

        assertThatThrownBy(() -> {
            validateIsNotNegativeMethod.invoke(inputView, "-1");
        });
    }

    @Test
    void validateMoreThanOneTicket_티켓_한장의_가격_이상이면_예외가_발생하지_않아야_함() throws NoSuchMethodException {
        InputView inputView = InputView.getInstance();

        Method validateMoreThanOneTicketMethod = InputView.class.getDeclaredMethod("validateMoreThanOneTicket", String.class);
        validateMoreThanOneTicketMethod.setAccessible(true);

        assertThatCode(() -> {
            validateMoreThanOneTicketMethod.invoke(inputView, "1000");
        }).doesNotThrowAnyException();
    }

    @Test
    void validateMoreThanOneTicket_티켓_한장의_가격보다_작으면_예외가_발생해야_함() throws NoSuchMethodException {
        InputView inputView = InputView.getInstance();

        Method validateMoreThanOneTicketMethod = InputView.class.getDeclaredMethod("validateMoreThanOneTicket", String.class);
        validateMoreThanOneTicketMethod.setAccessible(true);

        assertThatThrownBy(() -> {
            validateMoreThanOneTicketMethod.invoke(inputView, "999");
        });
    }

    @Test
    void validateTicketSize_로또_번호의_개수가_정상적으로_주어지면_예외가_발생하지_않아야_함() throws NoSuchMethodException {
        InputView inputView = InputView.getInstance();

        Method validateTicketSizeMethod = InputView.class.getDeclaredMethod("validateTicketSize", List.class);
        validateTicketSizeMethod.setAccessible(true);

        assertThatCode(() -> {
            validateTicketSizeMethod.invoke(inputView, Arrays.asList(1, 2, 3, 4, 5, 6));
        }).doesNotThrowAnyException();
    }

    @Test
    void validateTicketSize_로또_번호의_개수가_비정상적으로_주어지면_예외가_발생해야_함() throws NoSuchMethodException {
        InputView inputView = InputView.getInstance();

        Method validateTicketSizeMethod = InputView.class.getDeclaredMethod("validateTicketSize", List.class);
        validateTicketSizeMethod.setAccessible(true);

        assertThatThrownBy(() -> {
            validateTicketSizeMethod.invoke(inputView, Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        });

        assertThatThrownBy(() -> {
            validateTicketSizeMethod.invoke(inputView, Arrays.asList(1, 2, 3, 4, 5));
        });

        assertThatThrownBy(() -> {
            validateTicketSizeMethod.invoke(inputView, Arrays.asList(1, 2, 3, 4, 5, 5));
        });
    }

    @Test
    void validateIsInLottoNumberRange_정상_범위의_번호가_주어지면_예외가_발생하지_않아야_함() throws NoSuchMethodException {
        InputView inputView = InputView.getInstance();

        Method validateIsInLottoNumberRange = InputView.class.getDeclaredMethod("validateIsInLottoNumberRange", String.class);
        validateIsInLottoNumberRange.setAccessible(true);

        assertThatCode(() -> {
            validateIsInLottoNumberRange.invoke(inputView, "45");
        }).doesNotThrowAnyException();
    }

    @Test
    void validateIsInLottoNumberRange_비정상_범위의_번호가_주어지면_예외가_발생해야_함() throws NoSuchMethodException {
        InputView inputView = InputView.getInstance();

        Method validateIsInLottoNumberRange = InputView.class.getDeclaredMethod("validateIsInLottoNumberRange", String.class);
        validateIsInLottoNumberRange.setAccessible(true);

        assertThatThrownBy(() -> {
            validateIsInLottoNumberRange.invoke(inputView, "46");
        });
    }
}