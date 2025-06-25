package brique;

import brique.controller.GameController;
import brique.model.Move;
import brique.model.Player;
import brique.model.UnadmissibleMove;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class EscortRuleTest {

    @Test void escortPlacesStone() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);

        game.makeMove(Move.normal(7, 9, player_1));
        game.makeMove(Move.normal(0, 0, player_2));
        game.makeMove(Move.normal(8, 8, player_1));

        assertEquals(player_1, game.board().getPlayerAt(7, 8));
    }

    @Test void escortCapturesOpponent() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);

        game.makeMove(Move.normal(7, 9, player_1));
        game.makeMove(Move.normal(7, 8, player_2));
        game.makeMove(Move.normal(8, 8, player_1));

        assertEquals(player_1, game.board().getPlayerAt(7, 8));
    }

    @Test void escortRulePlaceOnBlackCell() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);

        game.makeMove(Move.normal(7, 8, player_1));
        game.makeMove(Move.normal(0, 0, player_2));
        game.makeMove(Move.normal(6, 9, player_1));

        assertEquals(player_1, game.board().getPlayerAt(7, 9));
    }

    @Test void doubleEscortMoveBlackCell() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);

        game.makeMove(Move.normal(6, 9, player_1));
        game.makeMove(Move.normal(0, 0, player_2));
        game.makeMove(Move.normal(8, 7, player_1));
        game.makeMove(Move.normal(0, 1, player_2));
        game.makeMove(Move.normal(7, 8, player_1));

        assertEquals(player_1, game.board().getPlayerAt(7, 9));
        assertEquals(player_1, game.board().getPlayerAt(8, 8));
    }
}
