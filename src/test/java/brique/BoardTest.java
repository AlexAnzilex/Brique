package brique;

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
    public void positionPlayerInFreePlace() {
        Board board = new Board();
        Player player_1 = new  Player("Player_1");
        Player player_2 = new  Player("Player_2");

        board.placePlayer(0, 0, player_1);
        assertThrows(IllegalArgumentException.class, () -> board.placePlayer(0, 0, player_2));

    }
}



