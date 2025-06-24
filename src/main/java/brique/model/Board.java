package brique.model;


import java.util.Arrays;

public class Board {
    private final int rows;
    private final int cols;
    private final Player[][] grid;
    private final Player defaultPlayer = new Player("None");

    public Board(int size) {
        this.rows = size;
        this.cols = size;
        this.grid = new Player[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = defaultPlayer;
            }
        }
    }

    public Board() {
        this(15);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean isFree(int row, int col) {
        checkBounds(row, col);
        return grid[row][col].equals(defaultPlayer);
    }


    public void placeStone(Move move) {
        if (!isFree(move.getRow(), move.getCol())) {
            throw new IllegalArgumentException("Cell (" + move.getRow() + "," + move.getCol() + ") is already occupied");
        }
        grid[move.getRow()][move.getCol()] = move.getPlayer();
    }

    public void PlaceStonePieRule(int row, int col, Player player, boolean pieMove) {
        checkBounds(row, col);
        if (!pieMove) {
            if (!isFree(row, col))
                throw new IllegalArgumentException("Overwrite not allowed without Pie-Rule");
        }
        grid[row][col] = player;
    }

    public void PlaceStoneEscortRule(Move move) {
        if (!boundsWithin(move.getRow(), move.getCol())) return;

        Player cellPlayer = getPlayerAt(move.getRow(), move.getCol());
        if (!cellPlayer.equals(move.getPlayer()) && !cellPlayer.equals(defaultPlayer)) {
            grid[move.getRow()][move.getCol()] = defaultPlayer;
        }
        if (isFree(move.getRow(), move.getCol())) {
            grid[move.getRow()][move.getCol()] = move.getPlayer();
        }
    }


    public Player getPlayerAt(int row, int col) {
        boundsWithin(row, col);
        return grid[row][col];
    }

    public boolean boundsWithin(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
    private void checkBounds(int row,int col){
        if(!boundsWithin(row,col))
            throw new IndexOutOfBoundsException("Invalid position (" + row +"," + col + ")");}

    @Override
    public String toString() {
        String string = "";
        for (Player[] row : grid) {
            string += Arrays.toString(row) + "\n";
        }
        return string;
    }
}
