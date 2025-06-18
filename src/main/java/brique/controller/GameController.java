package brique.controller;

import brique.model.Player;

public class GameController {
    private final Player firstPlayer;
    private final Player secondPlayer;

    public GameController(Player player_1, Player player_2) {
        this.firstPlayer = player_1;
        this.secondPlayer = player_2;
    }

    public Object currentPlayer() {
        return firstPlayer;
    }
}
