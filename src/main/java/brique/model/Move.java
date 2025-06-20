package brique.model;

public class Move {
    private final int row;
    private final int col;
    private final Player player;
    private final boolean pieMove;

    public Move(int row, int col, Player player, boolean pieMove) {
        this.row = row;
        this.col = col;
        this.player = player;
        this.pieMove = pieMove;
    }
    public Move(int row, int col, Player player) {
        this.row = row;
        this.col = col;
        this.player = player;
        this.pieMove = false;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public Player getPlayer() {
        return player;
    }
    public boolean isPieMove() {return pieMove;}
}
