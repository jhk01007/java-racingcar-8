package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

import static racingcar.util.CarRacingValidator.*;

public class CarRacing {

    private final List<Car> cars;
    private final List<RacingRecord> racingRecords;
    private final List<String> winners;
    private final int roundCount;

    private CarRacing(List<Car> cars, List<RacingRecord> racingRecords, List<String> winners, int roundCount) {
        this.cars = cars;
        this.racingRecords = racingRecords;
        this.winners = winners;
        this.roundCount = roundCount;
    }

    public List<RacingRecord> getRacingRecords() {
        return Collections.unmodifiableList(racingRecords);
    }

    public List<String> getWinners() {
        return Collections.unmodifiableList(winners);
    }

    public static CarRacing create(List<Car> cars, int roundCount) {
        validateCarNameListSize(cars);
        validateCarNameDuplicate(cars);
        validateRoundCount(roundCount);
        return new CarRacing(cars, new ArrayList<>(), new ArrayList<>(), roundCount);
    }

    public void startRace() {
        Map<String, Integer> carPositionMap = createInitPositionMap();
        for (int curRound = 1; curRound <= roundCount; curRound++) {
            // 각 차에 대한 이동여부 결정
            moveCars(carPositionMap);

            // 현재 라운드에 대한 기록을 저장
            HashMap<String, Integer> curRoundResult = new HashMap<>(carPositionMap);
            racingRecords.add(RacingRecord.create(curRoundResult));
        }

        // 레이싱 결과 처리
        processRaceResult();
    }

    private Map<String, Integer> createInitPositionMap() {
        Map<String, Integer> carPositions = new LinkedHashMap<>(cars.size());
        for (Car car : cars) {
            carPositions.put(car.getName(), 0); // 현재 위치를 0으로 초기화
        }
        return carPositions;
    }

    private void moveCars(Map<String, Integer> carPostionMap) {
        for (Car car : cars) {
            int randomNumber = Randoms.pickNumberInRange(0, 9);
            if (randomNumber >= 4) {
                // 난수가 4 이상이면 1 만큼 이동
                carPostionMap.put(car.getName(), carPostionMap.get(car.getName()) + 1);
            }
        }
    }

    private void processRaceResult() {
        RacingRecord racingResult = racingRecords.getLast();
        winners.addAll(racingResult.getTopCarName());
    }
}
