package racingcar.view;

import racingcar.dto.RacingGameResponseDto;
import racingcar.mapper.CarRacingMapper;

public class ConsoleRacingGameOutputView implements RacingGameOutputView {


    @Override
    public void writeResult(RacingGameResponseDto responseDto) {
        System.out.println("실행 결과");

        // 라운드별 결과 출력
        System.out.println(CarRacingMapper.toRawRaceRecord(responseDto.racingRecordDtos()));
        // 최종 우승자 출력
        System.out.printf("최종 우승자 : %s\n", CarRacingMapper.toRawWinner(responseDto.winners()));
    }
}
