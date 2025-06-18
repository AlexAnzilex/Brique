package brique.controller;

import brique.model.Board;
import brique.model.Player;

public class GameController {
    private final Player firstPlayer;
    private final Player secondPlayer;
    private final Board board;
    private int turn;

    public GameController(Player player_1, Player player_2) {
        this.firstPlayer = player_1;
        this.secondPlayer = player_2;
        this.board = new Board();
        this.turn = 1;
    }

    public Player currentPlayer() {
        return (turn % 2 == 1) ? firstPlayer : secondPlayer;
    }

    public Board board() {
        return board;
    }

    public int currentTurn() {
        return turn;
    }

    public void setTurnForTest(int t) {
        this.turn = t;
    }

    public void makeMove(int row, int col) {
        board.placeStone(row, col, currentPlayer());
        turn++;
    }
}
