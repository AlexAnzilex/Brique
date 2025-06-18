package brique.model;

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
        this(15); // default to 15×15
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean isFree(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Invalid position (" + row + "," + col + ")");
        }
        return grid[row][col].equals(defaultPlayer);
    }


    public void placeStone(int row, int col, Player player) {
        if (!isFree(row, col)) {
            throw new IllegalArgumentException("Cell (" + row + "," + col + ") is already occupied");
        }
        grid[row][col] = player;
    }

    public Player getPlayerAt(int row, int col) {
        return grid[row][col];
    }
}



