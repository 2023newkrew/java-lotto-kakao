package lotto.domain;

import static lotto.constant.MessageConstant.INVALID_REGISTER_LOTTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Customer {

    private final Tickets tickets;
    private List<Lotto> manualLottos;
    private List<Lotto> autoLottos;

    public Customer(int price) {
        this.tickets = new Tickets(price);
        manualLottos = new ArrayList<>();
        autoLottos = new ArrayList<>();
    }

    public void registerManualLottos(List<Lotto> manualLottos) {
        if (!this.manualLottos.isEmpty()) {
            throw new IllegalArgumentException(INVALID_REGISTER_LOTTO);
        }
        this.manualLottos = manualLottos;
        this.tickets.use(manualLottos.size());
    }

    public void registerAutoLottos(List<Lotto> autoLottos) {
        if (!this.autoLottos.isEmpty()) {
            throw new IllegalArgumentException(INVALID_REGISTER_LOTTO);
        }
        this.autoLottos = autoLottos;
        this.tickets.use(autoLottos.size());
    }

    public int getTicketCount() {
        return tickets.getCount();
    }

    public List<Lotto> getEveryLottos() {
        return Stream.concat(
                this.autoLottos != null ? this.autoLottos.stream() : Stream.empty(), this.manualLottos.stream())
                .collect(Collectors.toUnmodifiableList());
    }

    public int getAutoLottoSize() {
        return autoLottos.size();
    }

    public int getManualLottoSize() {
        return manualLottos.size();
    }

    public int getEveryLottoSize() {
        return autoLottos.size() + manualLottos.size();
    }
}


