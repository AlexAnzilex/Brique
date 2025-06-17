package brique.tests;

import brique.model.Board;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void boardCanBeCreated() {
        new Board(); // we just verify it doesn't throw
    }
}
