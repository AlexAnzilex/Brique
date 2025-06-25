package brique;

import brique.model.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BoardEqualityTest {

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
}
