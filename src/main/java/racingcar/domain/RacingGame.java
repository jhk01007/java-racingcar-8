package racingcar.domain;

import java.util.*;

import static racingcar.util.CarRacingValidator.*;

public class RacingGame {

    private final List<RacingCar> racingCars;
    private final List<RacingRecord> racingRecords;
    private final List<String> winners;
    private final int roundCount;

    private RacingGame(List<RacingCar> racingCars, List<RacingRecord> racingRecords, List<String> winners, int roundCount) {
        this.racingCars = racingCars;
        this.racingRecords = racingRecords;
        this.winners = winners;
        this.roundCount = roundCount;
    }

    public static RacingGame create(List<RacingCar> racingCars, int roundCount) {
        validateCarListSize(racingCars);
        validateCarNameDuplicate(racingCars);
        validateRoundCountIsBiggerThanZero(roundCount);
        return new RacingGame(racingCars, new ArrayList<>(), new ArrayList<>(), roundCount);
    }

    // 레이싱 결과를 반환하기 위한 임시 record 클래스. 단순 값만 반환하는 역할을 함
    public record RaceResult(List<RacingRecord> racingRecords, List<String> winners) {}

    public RaceResult startRace() {
        Map<String, Integer> carPositionMap = createInitPositionMap();
        for (int curRound = 1; curRound <= roundCount; curRound++) {
            // 각 자동차 이동
            moveCars(carPositionMap);

            // 현재 라운드에 대한 기록을 저장
            HashMap<String, Integer> curRoundResult = new HashMap<>(carPositionMap);
            racingRecords.add(RacingRecord.create(curRoundResult));
        }

        // 레이싱 결과 처리
        processRaceResult();

        return new RaceResult(
                Collections.unmodifiableList(this.racingRecords),
                Collections.unmodifiableList(this.winners)
        );
    }

    private Map<String, Integer> createInitPositionMap() {
        Map<String, Integer> carPositions = new LinkedHashMap<>(racingCars.size());
        for (RacingCar racingCar : racingCars) {
            carPositions.put(racingCar.getCarName(), 0); // 현재 위치를 0으로 초기화
        }
        return carPositions;
    }

    private void moveCars(Map<String, Integer> carPositionRecord) {
        for (RacingCar racingCar : racingCars) {
            racingCar.attemptMove();
            carPositionRecord.put(racingCar.getCarName(), racingCar.getPosition());
        }
    }

    private void processRaceResult() {
        RacingRecord racingResult = racingRecords.getLast();
        winners.addAll(racingResult.getTopCarName());
    }
}
