package brique;

public class Board {
    private final int rows = 15;
    private final int cols = 15;
    private final Player[][] grid;
    private final Player defaultPlayer = new Player("None");

    public Board() {
        grid = new Player[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = defaultPlayer;
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean isFree(int row, int col) {
        return grid[row][col].equals(defaultPlayer);
    }

}



