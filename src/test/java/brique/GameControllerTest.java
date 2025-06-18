package brique;

import brique.controller.GameController;
import brique.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class GameControllerTest {

    @Test
    public void firstPlayerStartsTheGame() {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController gamecontroller = new GameController(player_1,player_2);

        assertNotNull(gamecontroller);
    }
}
