package brique.model;

public class Move {
    private final int row;
    private final int col;
    private final Player player;
    private final boolean pie;

    public Move(int row, int col, Player player, boolean pie) {
        this.row = row;
        this.col = col;
        this.player = player;
        this.pie = pie;
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
    public boolean isPie() {return pie;}
}
