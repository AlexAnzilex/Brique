package brique.controller;

import brique.model.Board;
import brique.model.Move;
import brique.model.Player;
import brique.model.UnadmissibleMove;

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

    public boolean makeMove(Move move) throws UnadmissibleMove{
        if (move.isPieMove()){
            if (turn != 2){
                throw new UnadmissibleMove("Can't execute pie rule in turn "+ turn);
            }
            if (!move.getPlayer().equals(secondPlayer)){
                throw new UnadmissibleMove("Only the " + secondPlayer + " can use the Pie Rule in turn two");
            }
            int row = move.getRow();
            int col = move.getCol();


            if (!board.getPlayerAt(row, col ).equals(firstPlayer)){
                throw new UnadmissibleMove("Can't play pie move in position (" + row + "," + col + ")");
            }

            board.placeStone(row, col, secondPlayer);
            turn ++;
            return true;
        }
        if (!move.getPlayer().equals(currentPlayer())) {
            throw new UnadmissibleMove(move.getPlayer() + " is not your turn!");
        }
        if (!board.isFree(move.getRow(), move.getCol())){
            throw new UnadmissibleMove("The cell (" + move.getRow() + move.getCol() + ") is already occupied!");
        }
        board.placeStone(move.getRow(), move.getCol(), currentPlayer());
        turn++;
        return true;
    }
}
