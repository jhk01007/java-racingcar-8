package racingcar.util;

import racingcar.domain.Car;
import racingcar.domain.CarRacing;
import racingcar.domain.RacingCar;

import java.util.HashSet;
import java.util.List;

/**
 * 자동차 경주에 대한 비즈니스 규칙을 검증하는 클래스
 */
public class CarRacingValidator {

    public static void validateCarListSize(List<RacingCar> racingCars) {
        if (racingCars == null || racingCars.size() < 2) {
            throw new IllegalArgumentException("자동차는 최소 2대 이상이어야 합니다.");
        }
    }

    public static void validateCarNameDuplicate(List<RacingCar> racingCars) {
        List<String> carNameList = racingCars.stream()
                .map(RacingCar::getCarName)
                .toList();

        HashSet<String> carNameSet = new HashSet<>(carNameList);
        if (carNameSet.size() != carNameList.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }

    public static void validateCarNameLength(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("자동차의 이름은 최대 5글자까지 가능합니다.");
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("자동차의 이름은 최소 1글자 이상이어야 합니다.");
        }
    }

    public static void validateRoundCount(int roundCount) {
        if (roundCount < 1) {
            throw new IllegalArgumentException("라운드는 최소 1개 이상이어야 합니다.");
        }
    }
}
