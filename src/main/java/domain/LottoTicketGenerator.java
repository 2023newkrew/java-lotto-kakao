package domain;

import java.util.List;

public interface LottoTicketGenerator {
    LottoNumbers generate();
    List<LottoNumbers> generate(int lottoCount);
}
