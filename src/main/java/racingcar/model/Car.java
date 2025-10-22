package racingcar.model;

public class Car {
    private final String name;

    private Car(String name) {
        this.name = name;
    }

    public static Car create(String name) {
        validateSelf(name);
        return new Car(name);
    }

    private static void validateSelf(String name) {
        if(name.length() > 5) {
            throw new IllegalArgumentException("차의 이름은 최대 5글자까지 가능합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
