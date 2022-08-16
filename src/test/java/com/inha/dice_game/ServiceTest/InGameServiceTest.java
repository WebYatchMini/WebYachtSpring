package com.inha.dice_game.ServiceTest;

import com.inha.dice_game.DTO.Game.GameDTOCollection;
import com.inha.dice_game.Service.Game.InGameService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
public class InGameServiceTest {

    @Autowired
    InGameService inGameService;

    @Test
    public void SstraightTest()
    {
        GameDTOCollection.Progress progress = new GameDTOCollection.Progress();

        progress.setTotalDices(new ArrayList<>(Arrays.asList(1,2,3,4,1)));
        inGameService.calcPickAvailability(progress);

        Assertions.assertThat(progress.getPickAvailability().get("S.Straight")).isEqualTo(15);

        progress.setTotalDices(new ArrayList<>(Arrays.asList(2,3,4,5,6)));
        inGameService.calcPickAvailability(progress);

        Assertions.assertThat(progress.getPickAvailability().get("S.Straight")).isEqualTo(15);
        progress.setTotalDices(new ArrayList<>(Arrays.asList(1,3,4,5,6)));
        inGameService.calcPickAvailability(progress);

        Assertions.assertThat(progress.getPickAvailability().get("S.Straight")).isEqualTo(15);
    }

}
