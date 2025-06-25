package brique.controller;
import brique.model.Board;
import brique.model.Player;

public class WinDetector {
    private final Player firstPlayer;
    private final Player secondPlayer;

    public WinDetector(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public boolean hasWon(Board board, Player player) {
        int rows = board.getRows();
        int cols = board.getCols();
        boolean[][] visited = new boolean[rows][cols];
        java.util.ArrayDeque<int[]> queue = new java.util.ArrayDeque<>();

        if (player.equals(firstPlayer)) {
            for (int col = 0; col < cols; col++) {
                if (board.getPlayerAt(0, col).equals(player)) {
                    queue.push(new int[]{0, col});
                    visited[0][col] = true;
                }
            }
        } else if (player.equals(secondPlayer)) {
            for (int row = 0; row < rows; row++) {
                if (board.getPlayerAt(row, 0).equals(player)) {
                    queue.push(new int[]{row, 0});
                    visited[row][0] = true;
                }
            }
        } else {
            return false;
        }

        while (!queue.isEmpty()) {
            int[] cell = queue.pop();
            int row = cell[0];
            int col = cell[1];

            if ((player.equals(firstPlayer) && row == rows - 1) ||
                    (player.equals(secondPlayer) && col == cols - 1)) {
                return true;
            }

            int[][] neighbours = {{row - 1, col}, {row + 1, col}, {row, col - 1}, {row, col + 1}};
            for (int[] n : neighbours) {
                int r = n[0];
                int c = n[1];
                if (r >= 0 && r < rows && c >= 0 && c < cols &&
                        !visited[r][c] && board.getPlayerAt(r, c).equals(player)) {
                    visited[r][c] = true;
                    queue.push(new int[]{r, c});
                }
            }
        }
        return false;
    }
}