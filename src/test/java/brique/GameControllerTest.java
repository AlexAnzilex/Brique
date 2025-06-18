package brique;

import brique.controller.GameController;
import brique.model.Board;
import brique.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GameControllerTest {

    @Test
    public void firstPlayerStartsTheGame() {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController gamecontroller = new GameController(player_1,player_2);

        assertEquals(player_1, gamecontroller.currentPlayer());
    }

    @Test
    public void boardInitialized() {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");

        GameController gamecontroller = new GameController(player_1,player_2);

        assertNotNull(gamecontroller.board());
    }

    @Test
    public void boardIsEmptyAtStart() {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");

        GameController game = new GameController(player_1,player_2);
        Board board = game.board();

        for (int row = 0; row< board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                assertTrue(board.isFree(row, col),
                        "Expected cell (" + row + "," + col + ") to be free");
            }
        }
    }

    @Test
    public void currentTurnCounter() {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");

        GameController game = new GameController(player_1,player_2);

        assertDoesNotThrow(() -> game.currentTurn());
    }


}
