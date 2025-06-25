package brique.controller;

import brique.model.Board;
import brique.model.Player;

public class WinDetector {
    private final TurnManager turns;

    public WinDetector(TurnManager turns) {
        this.turns = turns;
    }

    public boolean hasWon(Board board, Player player) {
        int rows = board.getRows();
        int cols = board.getCols();
        boolean[][] visited = new boolean[rows][cols];
        java.util.ArrayDeque<int[]> q = new java.util.ArrayDeque<>();

        if (player.equals(turns.firstPlayer())) {
            for (int c = 0; c < cols; c++)
                if (board.getPlayerAt(0, c).equals(player)) {
                    q.push(new int[]{0, c});
                    visited[0][c] = true;
                }
        } else if (player.equals(turns.secondPlayer())) {
            for (int r = 0; r < rows; r++)
                if (board.getPlayerAt(r, 0).equals(player)) {
                    q.push(new int[]{r, 0});
                    visited[r][0] = true;
                }
        } else return false;

        while (!q.isEmpty()) {
            int[] cell = q.pop();
            int r = cell[0], c = cell[1];

            if ((player.equals(turns.firstPlayer()) && r == rows - 1) ||
                    (player.equals(turns.secondPlayer()) && c == cols - 1))
                return true;

            int[][] nbs = {{r - 1, c}, {r + 1, c}, {r, c - 1}, {r, c + 1}};
            for (int[] n : nbs) {
                int nr = n[0], nc = n[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols &&
                        !visited[nr][nc] && board.getPlayerAt(nr, nc).equals(player)) {
                    visited[nr][nc] = true;
                    q.push(new int[]{nr, nc});
                }
            }
        }
        return false;
    }
}
