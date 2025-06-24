package brique.controller;

import brique.model.*;

public class GameController {
    private Player firstPlayer;
    private Player secondPlayer;
    private final Board board;
    private final EscortRuleEngine escortEngine;
    private int turn;

    public GameController(Player player_1, Player player_2) {
        this.firstPlayer = player_1;
        this.secondPlayer = player_2;
        this.board = new Board();
        this.turn = 1;
        this.escortEngine = new EscortRuleEngine(board);
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }
    public Player getSecondPlayer() {
        return secondPlayer;
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

    public boolean pieRuleAvailable(){return turn == 2;}
    public void applyPieMove(Move move) throws UnadmissibleMove {
        if (turn!=2) {
            throw new UnadmissibleMove("Can't execute pie rule in turn "+ turn);
        }
        if (!move.getPlayer().name().equals(secondPlayer.name())) {
            throw new UnadmissibleMove("Only the " + secondPlayer.name() + " can use the Pie Rule in turn two");
        }

        int row=-1;
        int col=-1;
        search:
        for (int i = 0; i < board.getRows(); i++){
            for (int j = 0; j < board.getCols(); j++){
                if (board.getPlayerAt(i,j).equals(firstPlayer)){
                    row=i;
                    col=j;
                    break search;
                }
            }
        }
        if (row<0) throw new IllegalStateException("Did not find the first player in board");
        board.PlaceStonePieRule(row, col, secondPlayer, true);

        Player tmp = firstPlayer;
        firstPlayer = secondPlayer;
        secondPlayer = tmp;

    }


    public boolean makeMove(Move move) throws UnadmissibleMove{
        if (move.isPieMove()) throw new UnadmissibleMove("Use applyPieRule()");
        if (!move.getPlayer().equals(currentPlayer())) {
            throw new UnadmissibleMove(move.getPlayer() + " is not your turn!");
        }
        if (!board.isFree(move.getRow(), move.getCol())){
            throw new UnadmissibleMove("The cell (" + move.getRow() + move.getCol() + ") is already occupied!");
        }
        board.placeStone(move.getRow(), move.getCol(), currentPlayer());
        escortEngine.applyRules(move);
        turn++;
        return true;
    }


    public boolean winBoard() {

         if (goalWinner((r,c) -> r==0,
                 (r, c) -> r==board.getRows()-1,
                 firstPlayer)) return true;
         return goalWinner((r,c) -> c==0,
                 (r, c) -> c==board.getCols()-1,
                 secondPlayer);

    }

    private boolean goalWinner(java.util.function.BiPredicate<Integer, Integer> seed,
                                java.util.function.BiPredicate<Integer, Integer> goal,
                                Player player)
    {
        int rows = board().getRows();
        int cols = board().getCols();

        boolean[][] visited = new  boolean[rows][cols];
        java.util.ArrayDeque<int[]> queue = new java.util.ArrayDeque<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (seed.test(row,col) && board.getPlayerAt(row, col).equals(player)) {
                    queue.push(new int[]{row, col});
                    visited[row][col] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cell = queue.pop();
            int row = cell[0];
            int col = cell[1];

            if(goal.test(row,col)) {
                return true;
            }

            int[][] neighbour = {{row - 1, col}, {row + 1, col}, {row, col-1}, {row, col+1}};
            for (int[] n :  neighbour) {
                int r = n[0];
                int c = n[1];
                if (r>= 0 && r < rows && c >= 0 && c < cols
                        && !visited[r][c]
                        && board.getPlayerAt(r, c).equals(player)) {
                    visited[r][c] = true;
                    queue.push(new int[]{r, c});

                }
            }

        }
        return false;
    }


}
