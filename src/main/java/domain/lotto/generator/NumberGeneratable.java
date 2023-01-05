package domain.lotto.generator;

import domain.lotto.ticket.LottoNumber;

import java.util.List;

@FunctionalInterface
public interface NumberGeneratable {
    List<LottoNumber> generate();
}
