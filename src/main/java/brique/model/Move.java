package brique.model;


public class Move {
    private final int row;
    private final int col;
    private final Player player;
    private final MoveType type;


    public Move(int row, int col, Player player) {
      this(row, col, player, MoveType.NORMAL);

    }

    public Move(int row, int col, Player player, MoveType type) {
        this.row = row;
        this.col = col;
        this.player = player;
        this.type = type;
    }

    public static Move normal(int row, int col, Player player) {
        return new Move(row, col, player, MoveType.NORMAL);
    }

    public static Move pie(int row, int col, Player player) {
        return new Move(row, col, player, MoveType.PIE);
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public Player getPlayer() {return player;}
    public boolean isPieMove() {return type == MoveType.PIE;}
}
