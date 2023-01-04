package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TicketTest {
    @Test
    @DisplayName("can validate incorrect range of number")
    void can_validate_incorrect_range_of_numbers() {
        assertFalse(Ticket.isValidNumber(0));
        assertTrue(Ticket.isValidNumber(Ticket.NUMBERS_RANGE));
        assertFalse(Ticket.isValidNumber(Ticket.NUMBERS_RANGE + 1));
    }

    @Test
    @DisplayName("can validate incorrect length of numbers")
    void can_validate_incorrect_length_of_numbers() {
        List<Integer> testNumbers = new ArrayList<>();
        for (int i = 1; i < Ticket.NUMBERS_LENGTH; i++) {
            testNumbers.add(i);
        }
        assertFalse(Ticket.isValidNumbers(testNumbers));

        testNumbers.add(Ticket.NUMBERS_LENGTH);
        assertTrue(Ticket.isValidNumbers(testNumbers));

        testNumbers.add(Ticket.NUMBERS_LENGTH + 1);
        assertFalse(Ticket.isValidNumbers(testNumbers));
    }
}