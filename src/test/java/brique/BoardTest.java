package brique;

import brique.Board;
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
}


