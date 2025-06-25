package brique;

import brique.controller.GameController;
import brique.model.Move;
import brique.model.Player;
import brique.model.UnadmissibleMove;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnManagementTest {

    @Test void alternatePlayers() {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);

        assertEquals(player_1, game.currentPlayer());
        game.setTurnForTest(2);
        assertEquals(player_2, game.currentPlayer());
    }

    @Test void turnCounterIncrements() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);

        assertEquals(1, game.currentTurn());
        game.makeMove(Move.normal(0, 0, game.currentPlayer()));
        assertEquals(2, game.currentTurn());
        game.makeMove(Move.normal(0, 1, game.currentPlayer()));
        assertEquals(3, game.currentTurn());
    }

    @Test void currentPlayerChangesAfterMove() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);

        assertEquals(player_1, game.currentPlayer());
        game.makeMove(Move.normal(0, 0, game.currentPlayer()));
        assertEquals(player_2, game.currentPlayer());
    }
}
