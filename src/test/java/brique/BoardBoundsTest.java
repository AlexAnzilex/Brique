package brique;

import brique.model.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardBoundsTest {

    @Test
    public void cellShouldBeFreeAfterBoardCreation() {
        Board board = new Board();
        assertTrue(board.isFree(0, 0));
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
}
