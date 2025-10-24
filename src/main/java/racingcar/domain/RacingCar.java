package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class RacingCar {

    private final Car car;
    private int position;

    private RacingCar(Car car, int position) {
        this.car = car;
        this.position = position;
    }

    public static RacingCar create(Car car, int position) {
        return new RacingCar(car, position);
    }

    public String getCarName() {
        return car.getName();
    }

    public int getPosition() {
        return position;
    }

    public void attemptMove() {
        if (Randoms.pickNumberInRange(0, 9) >= 4) {
            // 난수가 4 이상이면 1 만큼 이동
            position++;
        }
    }
}
