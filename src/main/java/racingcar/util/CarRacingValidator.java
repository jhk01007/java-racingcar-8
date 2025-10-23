package racingcar.util;

import java.util.HashSet;
import java.util.List;

/**
 * 자동차 경주에 대한 비즈니스 규칙을 검증하는 클래스
 */
public class CarRacingValidator {


    public static void validateRequest(List<String> carNameList, int roundCount) {
        validateCarNameList(carNameList);
        validateCarNameDuplicate(carNameList);
        validateRoundCount(roundCount);
    }
    private static void validateCarNameList(List<String> carNameList) {
        if (carNameList == null || carNameList.size() < 2) {
            throw new IllegalArgumentException("자동차는 최소 2대 이상이어야 합니다.");
        }
    }

    private static void validateCarNameDuplicate(List<String> carNameList) {
        HashSet<String> carNameSet = new HashSet<>(carNameList);
        if (carNameSet.size() != carNameList.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }

    private static void validateRoundCount(int roundCount) {
        if (roundCount < 1) {
            throw new IllegalArgumentException("라운드는 최소 1개 이상이어야 합니다.");
        }
    }
}
