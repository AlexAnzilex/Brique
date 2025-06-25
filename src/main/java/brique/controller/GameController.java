package brique.controller;

import brique.model.*;

public class GameController {
    private final Board board;
    private final EscortRuleEngine escortEngine;
    private final TurnManager turns;
    private final WinDetector winDetector;


    public GameController(Player player_1, Player player_2) {
        this.board = new Board();
        this.turns = new TurnManager(player_1, player_2);
        this.escortEngine = new EscortRuleEngine(board);
        this.winDetector = new WinDetector(player_1, player_2);
    }

    public Player getFirstPlayer() {
        return turns.firstPlayer();
    }

    public Player getSecondPlayer() {
        return turns.secondPlayer();
    }

    public Player currentPlayer() {
        return turns.currentPlayer();
    }

    public Board board() {
        return board;
    }

    public int currentTurn() {
        return turns.currentTurn();
    }

    public void setTurnForTest(int t) {
        turns.setTurn(t);
    }

    public boolean pieRuleAvailable() {
        return turns.currentTurn() == 2;
    }

    public boolean isBlack(Player p) {
        return p.equals(turns.firstPlayer());
    }

    public void applyPieMove() throws UnadmissibleMove {
        if (turns.currentTurn() != 2)
            throw new UnadmissibleMove("Can't execute pie rule in turn " + turns.currentTurn());
        if (!currentPlayer().equals(turns.secondPlayer()))
            throw new UnadmissibleMove("Only the " + turns.secondPlayer().name() + " can use the Pie Rule in turn two");

        int row = -1, col = -1;
        search:
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                if (board.getPlayerAt(i, j).equals(turns.firstPlayer())) {
                    row = i;
                    col = j;
                    break search;
                }
            }
        }
        if (row < 0) throw new IllegalStateException("Did not find the first player in board");

        board.placeStonePieRule(row, col, turns.secondPlayer());
        turns.swapPlayers();
    }

    public boolean makeMove(Move move) throws UnadmissibleMove {
        if (move.isPieMove())
            throw new UnadmissibleMove("Use applyPieRule()");
        if (!move.getPlayer().equals(currentPlayer()))
            throw new UnadmissibleMove(move.getPlayer() + " is not your turn!");
        if (!board.isFree(move.getRow(), move.getCol()))
            throw new UnadmissibleMove("The cell (" + move.getRow() + "," + move.getCol() + ") is already occupied!");

        board.placeStone(move);
        escortEngine.applyRules(move);
        winDetector.hasWon(board, move.getPlayer());
        turns.nextTurn();
        return true;
    }

    public boolean winBoard() {
        return winDetector.hasWon(board, turns.firstPlayer()) ||
                winDetector.hasWon(board, turns.secondPlayer());
    }
}