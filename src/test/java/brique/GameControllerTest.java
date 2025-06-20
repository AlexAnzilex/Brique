package brique;

import brique.controller.GameController;
import brique.model.Board;
import brique.model.Move;
import brique.model.Player;
import brique.model.UnadmissibleMove;
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
    public void pieMoveFailIfNotTurn2()  throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);

        game.makeMove(new Move(1,1,player_1));

        Move WrongMove = new Move(2, 2, player_1, true);

        assertThrows(UnadmissibleMove.class, () -> game.makeMove(WrongMove));

    }

    @Test
    public void pieRuleFailAfterTurnTwo() throws UnadmissibleMove {
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");
        GameController game = new GameController(player_1,player_2);

        game.makeMove(new Move(0,0,player_1));
        game.makeMove(new Move(2,2,player_2));
        game.makeMove(new Move(1,1,player_1));
        Move latePieMove= new Move(0,0,player_2, true);

        assertThrows(UnadmissibleMove.class, () -> game.makeMove(latePieMove));
    }
}
