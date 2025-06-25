package brique;

import brique.model.Board;
import brique.model.Player;
import brique.model.Move;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardPlacementTest {

    @Test
    public void positionPlayerInFreePlace() {
        Board board = new Board();
        Player player_1 = new Player("Player_1");
        Player player_2 = new Player("Player_2");

        board.placeStone(new Move(0, 0, player_1));
        assertThrows(IllegalArgumentException.class,
                () -> board.placeStone(new Move(0, 0, player_2)));
    }

    @Test
    public void positionPlayerIsCorrect() {
        Board board = new Board();
        Player player = new Player("Player");

        board.placeStone(new Move(0, 0, player));
        assertEquals(player, board.getPlayerAt(0, 0));
    }
}
