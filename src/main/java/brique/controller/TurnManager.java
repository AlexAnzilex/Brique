package brique.controller;

import brique.model.Player;

public class TurnManager {
    private int turn = 1;
    private Player first;
    private Player second;

    public TurnManager(Player first, Player second) {
        this.first = first;
        this.second = second;
    }

    public int currentTurn() {
        return turn;
    }

    public Player currentPlayer() {
        return (turn % 2 == 1) ? first : second;
    }

    public Player firstPlayer() {
        return first;
    }

    public Player secondPlayer() {
        return second;
    }

    public void nextTurn() {
        turn++;
    }

    public void swapPlayers() {
        Player tmp = first;
        first = second;
        second = tmp;
    }

    void setTurn(int t) {
        this.turn = t;
    }
}
