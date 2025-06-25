package brique;

import brique.controller.GameController;
import brique.model.Board;
import brique.model.Move;
import brique.model.Player;
import brique.model.UnadmissibleMove;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WinDetectionTest {

    @Test void noWinOnNewGame() {
        GameController game = new GameController(new Player("Player_1"), new Player("Player_2"));
        assertFalse(game.winBoard());
    }

    @Test void player1WinsTopToBottom() {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);
        Board board = game.board();

        for (int row = 0; row < board.getRows(); row++)
            board.placeStone(Move.normal(row, 0, player_1));

        assertTrue(game.winBoard());
    }

    @Test
    public void player2WinByConnectingLeftRight() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);
        Board board = game.board();

        game.makeMove(Move.normal(5,5, game.currentPlayer()));
        for (int col=0; col<board.getRows(); col++) {
            board.placeStone(Move.normal(0, col, player_2));
        }
        assertTrue(game.winBoard());
    }

    @Test
    public void player2SWin() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);
        Board board = game.board();
        int[][] pos_list = {
                {1,0},{1,1},{1,2},{1,3},
                {2,3},
                {3,1},{3,2},{3,3},
                {4,1},
                {5,1},{5,2},{5,3},{5,4},{5,5},{5,6},{5,7},{5,8},{5,9},{5,10},{5,11},{5,12},{5,13},{5,14}
        };
        for (int[] pos : pos_list) {
            board.placeStone(Move.normal(pos[0], pos[1], player_2));
        }
        assertTrue(game.winBoard());
    }
}
