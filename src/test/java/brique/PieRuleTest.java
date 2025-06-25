package brique;

import brique.controller.GameController;
import brique.model.Move;
import brique.model.Player;
import brique.model.UnadmissibleMove;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieRuleTest {

    @Test void pieRuleSuccess() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);

        game.makeMove(Move.normal(0, 0, player_1));
        assertTrue(game.pieRuleAvailable());
        game.applyPieMove();

        assertEquals(player_2, game.getFirstPlayer());
        assertEquals(player_1, game.getSecondPlayer());
        assertEquals(player_2, game.board().getPlayerAt(0, 0));
        assertEquals(player_1, game.currentPlayer());
    }

    @Test void pieRuleFailsIfNotTurn2() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);

        game.makeMove(Move.normal(0, 0, player_1));
        game.makeMove(Move.normal(1, 1, player_2));

        assertThrows(UnadmissibleMove.class, game::applyPieMove);
    }


    @Test void pieRuleWrongPosition() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);

        game.makeMove(Move.normal(6, 6, player_1));
        Move wrong = Move.pie(7, 7, player_2);
        assertThrows(UnadmissibleMove.class, () -> game.makeMove(wrong));
    }
}
