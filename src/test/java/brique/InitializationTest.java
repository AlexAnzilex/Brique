package brique;

import brique.controller.GameController;
import brique.model.Board;
import brique.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitializationTest {

    @Test void firstPlayerStarts() {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController gamecontroller = new GameController(player_1, player_2);
        assertEquals(player_1, gamecontroller.currentPlayer());
    }

    @Test void boardCreated() {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController gamecontroller = new GameController(player_1, player_2);
        assertNotNull(gamecontroller.board());
    }

    @Test void boardEmptyAtStart() {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);
        Board board = game.board();
        for (int row = 0; row < board.getRows(); row++)
            for (int col = 0; col < board.getCols(); col++)
                assertTrue(board.isFree(row, col));
    }

    @Test void initialTurnIsOne() {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);
        assertEquals(1, game.currentTurn());
    }
}
