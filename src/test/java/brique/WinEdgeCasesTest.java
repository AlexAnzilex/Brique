package brique;

import brique.controller.GameController;
import brique.model.Board;
import brique.model.Move;
import brique.model.Player;
import brique.model.UnadmissibleMove;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WinEdgeCasesTest {

    @Test void noWinAfterNonConnectingMoves() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);

        game.makeMove(Move.normal(0, 0, game.currentPlayer()));
        game.makeMove(Move.normal(7, 7, game.currentPlayer()));
        game.makeMove(Move.normal(1, 0, game.currentPlayer()));

        assertFalse(game.winBoard());
    }

    @Test void player1NotWinForAStone() {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);
        Board board = game.board();

        for (int row = 0; row < board.getRows() - 1; row++)
            board.placeStone(Move.normal(row, 0, player_1));

        assertFalse(game.winBoard());
    }

    @Test void player2Win() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);
        Board board = game.board();

        game.makeMove(Move.normal(5, 5, game.currentPlayer())); // player_1
        for (int col = 0; col < board.getCols(); col++)
            board.placeStone(Move.normal(7, col, player_2));

        assertTrue(game.winBoard());
    }
}
