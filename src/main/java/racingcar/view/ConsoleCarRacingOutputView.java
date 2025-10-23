package racingcar.view;

import racingcar.dto.CarRacingResponseDto;
import racingcar.mapper.CarRacingMapper;

public class ConsoleCarRacingOutputView implements CarRacingOutputView {


    @Override
    public void writeResult(CarRacingResponseDto responseDto) {
        System.out.println("실행 결과");

        // 라운드별 결과 출력
        System.out.println(CarRacingMapper.toRawRaceRecord(responseDto.racingRecordDtos()));
        // 최종 우승자 출력
        System.out.printf("최종 우승자 : %s\n", CarRacingMapper.toRawWinner(responseDto.winners()));
    }
}
