package brique;

import brique.model.Board;
import brique.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void boardShouldHaveDefaultSize15x15() {
        Board board = new Board();
        assertEquals(15, board.getRows());
        assertEquals(15, board.getCols());
    }

    @Test
    public void cellShouldBeFreeAfterBoardCreation() {
        Board board = new Board();
        assertTrue(board.isFree(0, 0));
    }

    @Test
    public void boardShouldSupportCustomSquareSize() {
        Board board = new Board(10);
        assertEquals(10, board.getRows());
        assertEquals(10, board.getCols());
    }

    @Test
    public void accessingOutOfBoundsPositionShouldThrow() {
        Board board = new Board();

        Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> board.isFree(-1, 0));
        assertEquals("Invalid position (-1,0)", e1.getMessage());

        Exception e2 = assertThrows(IndexOutOfBoundsException.class, () -> board.isFree(0, -1));
        assertEquals("Invalid position (0,-1)", e2.getMessage());

        Exception e3 = assertThrows(IndexOutOfBoundsException.class, () -> board.isFree(0, 15));
        assertEquals("Invalid position (0,15)", e3.getMessage());

        Exception e4 = assertThrows(IndexOutOfBoundsException.class, () -> board.isFree(15, 0));
        assertEquals("Invalid position (15,0)", e4.getMessage());
    }

    @Test
    public void boardsHaveTheSameSize() {
        Board board_one = new Board();
        Board board_two = new Board();

        assertEquals(board_one.getRows(), board_two.getRows());
        assertEquals(board_one.getCols(), board_two.getCols());

        for (int row = 0; row < board_one.getRows(); row++) {
            for (int col = 0; col < board_one.getCols(); col++) {
                assertEquals(board_one.isFree(row, col), board_two.isFree(row, col));
            }
        }
    }


    @Test
    public void positionPlayerInFreePlace() {
        Board board = new Board();
        Player player_1 = new  Player("Player_1");
        Player player_2 = new  Player("Player_2");

        board.placePlayer(0, 0, player_1);
        assertThrows(IllegalArgumentException.class, () -> board.placePlayer(0, 0, player_2));

    }

    @Test
    public void positionPlayerIsCorrect() {
        Board board = new Board();
        Player player = new Player("Player");

        board.placePlayer(0, 0, player);
        assertEquals(player, board.getPlayerAt(0,0));
    }




}



