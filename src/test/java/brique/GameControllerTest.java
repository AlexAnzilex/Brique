package brique;

import brique.controller.GameController;
import brique.model.Board;
import brique.model.Move;
import brique.model.Player;
import brique.model.UnadmissibleMove;
import org.junit.jupiter.api.BeforeEach;
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

        assertEquals(1, game.currentTurn());
    }

    @Test
    public void alternatePlayerTurn() {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);

        assertEquals(player_1, game.currentPlayer());

        game.setTurnForTest(2);
        assertEquals(player_2, game.currentPlayer());
    }

    @Test
    public void playerCanPlaceStoneFreeCell() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);

        assertTrue(game.makeMove(new Move(0,0,game.currentPlayer())));

        assertEquals(player_1, game.board().getPlayerAt(0,0));

    }

    @Test
    public void turnIncreasesAfterTurn() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);

        assertEquals(1, game.currentTurn());
        assertTrue(game.makeMove(new Move(0,0,game.currentPlayer())));
        assertEquals(2, game.currentTurn());
        assertTrue(game.makeMove(new Move(0,1,game.currentPlayer())));
        assertEquals(3, game.currentTurn());
    }

    @Test
    public void currentPlayerChangeAfterMove() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);

        assertEquals(player_1, game.currentPlayer());
        assertTrue(game.makeMove(new Move(0,0, game.currentPlayer())));
        assertEquals(player_2, game.currentPlayer());
    }

    @Test
    public void moveWrongIsRejected() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);

        Move invalidMove = new Move(0,0,player_2);
        assertThrows(UnadmissibleMove.class, () -> game.makeMove(invalidMove));
    }

    @Test
    public void placeStoneOnOccupiedCell() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);

        assertDoesNotThrow(() -> game.makeMove(new Move(0,0,player_1)));
        assertThrows(UnadmissibleMove.class, () -> game.makeMove(new Move(0,0,player_2)));

    }

    @Test
    public void pieRuleSuccess() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1, player_2);

        game.makeMove(new Move(0, 0, player_1));
        assertTrue(game.pieRuleAvailable());
        game.applyPieMove();

        Player new_player_1 = game.getFirstPlayer();
        Player new_player_2 = game.getSecondPlayer();

        assertEquals(player_2, new_player_1);
        assertEquals(player_1, new_player_2);
    }

    @Test
    public void pieMoveFailIfNotTurn2()  throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);

        game.makeMove(new Move(1,1,game.currentPlayer()));

        game.makeMove(new Move(2, 2, game.currentPlayer()));

        assertThrows(UnadmissibleMove.class, game::applyPieMove);


    }


    @Test
    public void pieRuleWrongPosition() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);

        game.makeMove(new Move(6,6,player_1));
        Move WrongMove = new Move(7,7,player_2, true);
        assertThrows(UnadmissibleMove.class, () -> game.makeMove(WrongMove));
    }

    @Test
    public void escortRulePlace() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);

        game.makeMove( new Move(7, 9, player_1));
        game.makeMove( new Move(0, 0, player_2));
        game.makeMove( new Move(8, 8, player_1));

        assertEquals(player_1, game.board().getPlayerAt(7,8));

    }
    @Test
    public void escortRulePlaceEat() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);

        game.makeMove( new Move(7, 9, player_1));
        game.makeMove( new Move(7, 8, player_2));
        game.makeMove( new Move(8, 8, player_1));

        assertEquals(player_1, game.board().getPlayerAt(7,8));

    }

    @Test
    public void escortRulePlaceOnBlackCell() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);

        game.makeMove( new Move(7, 8, player_1));
        game.makeMove( new Move(0, 0, player_2));
        game.makeMove( new Move(6, 9, player_1));

        assertEquals(player_1, game.board().getPlayerAt(7,9));
    }

    @Test
    public void doubleEscortMoveBlackCell()  throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);

        game.makeMove( new Move(6, 9, player_1));
        game.makeMove( new Move(0, 0, player_2));
        game.makeMove( new Move(8, 7, player_1));
        game.makeMove( new Move(0, 1, player_2));
        game.makeMove( new Move(7, 8, player_1));

        assertEquals(player_1, game.board().getPlayerAt(7,9));
        assertEquals(player_1, game.board().getPlayerAt(8,8));
    }

    @Test
    public void noWinOnNewGame() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);
        assertFalse(game.winBoard(), "No winner at start of the game");
    }

    @Test
    public void noWinAfterNonConnectingMoves()  throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);

        game.makeMove( new Move(0, 0, game.currentPlayer()));
        game.makeMove( new Move(7, 7, game.currentPlayer()));
        game.makeMove( new Move(1, 0, game.currentPlayer()));

        assertFalse(game.winBoard());
    }

    @Test
    public void player1WinByConnectingTopBottom() {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);
        Board board = game.board();
        for (int row=0; row<board.getRows(); row++) {
            board.placeStone(row,0, player_1);
        }

        assertTrue(game.winBoard());
    }

    @Test
    public void player2WinByConnectingLeftRight() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);
        Board board = game.board();

        game.makeMove(new Move(5,5, game.currentPlayer()));
        for (int col=0; col<board.getRows(); col++) {
            board.placeStone(0, col, player_2);
        }
        assertTrue(game.winBoard());
    }
    @Test
    public void player2Win() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);
        Board board = game.board();

        game.makeMove(new Move(5,5, game.currentPlayer()));
        for (int col=0; col<board.getRows(); col++) {
            board.placeStone(7, col, player_2);
        }
        assertTrue(game.winBoard());
    }
}
