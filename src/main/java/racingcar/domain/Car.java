package racingcar.domain;


import racingcar.util.CarRacingValidator;

public class Car {
    private final String name;

    private Car(String name) {
        this.name = name;
    }

    public static Car create(String name) {
        CarRacingValidator.validateCarNameLength(name);
        return new Car(name);
    }

    public String getName() {
        return name;
    }
}
