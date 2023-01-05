package lotto.model;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;
import lotto.util.StringUtil;

public class LottoCount {
    private final Integer totalLottoCount;
    private final Integer manualLottoCount;
    private final Integer automaticLottoCount;

    public LottoCount(Integer totalLottoCount, String manualLottoCountString) {
        this.totalLottoCount = totalLottoCount;
        validateTypeOfManualLottoCount(manualLottoCountString);
        Integer manualLottoCount = StringUtil.convertStringToInt(manualLottoCountString);
        validateManualLottoCount(manualLottoCount);
        this.manualLottoCount = manualLottoCount;
        this.automaticLottoCount = this.totalLottoCount - this.manualLottoCount;
    }

    private void validateTypeOfManualLottoCount(String manualLottoCount) {
        try {
            StringUtil.convertStringToInt(manualLottoCount);
        } catch (NumberFormatException e) {
            throw new LottoException(ErrorCode.INVALID_INPUT_TYPE_NOT_INTEGER);
        }
    }

    private void validateManualLottoCount(Integer manualLottoCount) {
        if (totalLottoCount < manualLottoCount) {
            throw new LottoException(ErrorCode.LACK_OF_MONEY);
        }
        if (manualLottoCount < 0) {
            throw new LottoException(ErrorCode.INVALID_MANUAL_LOTTO_COUNT);
        }
    }

    public Integer getManualLottoCount() {
        return manualLottoCount;
    }

    public Integer getAutomaticLottoCount() {
        return automaticLottoCount;
    }
}
