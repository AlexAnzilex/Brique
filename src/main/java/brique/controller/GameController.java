package brique.controller;

import brique.model.Board;
import brique.model.Player;

public class GameController {
    private final Player firstPlayer;
    private final Player secondPlayer;
    private final Board board;
    private final int turn;

    public GameController(Player player_1, Player player_2) {
        this.firstPlayer = player_1;
        this.secondPlayer = player_2;
        this.board = new Board();
        this.turn = 1;
    }

    public Object currentPlayer() {
        return firstPlayer;
    }

    public Board board() {
        return board;
    }

    public int currentTurn() {
        return turn;
    }
}
