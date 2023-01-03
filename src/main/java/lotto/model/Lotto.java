package lotto.model;

import lotto.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final List<Ticket> tickets;
    private final RandomGenerator randomGenerator;

    public Lotto() {
        this.tickets = new ArrayList<>();

        List<Integer> generationRange = new ArrayList<>();
        for (int i = 1; i <= Ticket.NUMBERS_RANGE; i++) {
            generationRange.add(i);
        }
        this.randomGenerator = new RandomGenerator(generationRange);
    }

    public List<Ticket> getTickets() {
        return this.tickets;
    }

    public int getQuantity() {
        return this.tickets.size();
    }

    public void purchaseRandomLotto(long quantity) {
        for (int i = 0; i < quantity; i++) {
            this.tickets.add(new Ticket(this.randomGenerator.getOrderedNumbers(Ticket.NUMBERS_LENGTH)));
        }
    }
}
