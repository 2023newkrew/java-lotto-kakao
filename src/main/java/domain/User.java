package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class User {
    private final Wallet wallet;
    private final List<LottoTicket> manualTickets;
    private final List<LottoTicket> autoTickets;

    public User(Wallet wallet, List<LottoTicket> manualTickets, List<LottoTicket> autoTickets) {
        this.wallet = wallet;
        this.manualTickets = manualTickets;
        this.autoTickets = autoTickets;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public List<LottoTicket> getManualTickets() {
        return manualTickets;
    }

    public List<LottoTicket> getAutoTickets() {
        return autoTickets;
    }

    public List<LottoTicket> getLottoTickets(){
        return Stream.concat(autoTickets.stream(), manualTickets.stream())
                .collect(Collectors.toList());
    }
}
