package racingcar.mapper;

import racingcar.model.Car;

public class CarDomainMapper {

    public static Car toDomain(String carName) {
        return Car.create(carName);
    }
}
