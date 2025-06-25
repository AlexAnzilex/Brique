package brique.controller;
import brique.model.Move;
import brique.model.*;

public class GameController {
    private Player firstPlayer;
    private Player secondPlayer;
    private final Board board;
    private final EscortRuleEngine escortEngine;
    private int turn;

    public GameController(Player player_1, Player player_2, int dim) {
        this.firstPlayer = player_1;
        this.secondPlayer = player_2;
        this.board = new Board(dim);
        this.turn = 1;
        this.escortEngine = new EscortRuleEngine(board);
    }

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

    public boolean isBlack(Player p){ return p.equals(firstPlayer);}

    public void applyPieMove() throws UnadmissibleMove {
        if (turn!=2) {
            throw new UnadmissibleMove("Can't execute pie rule in turn "+ turn);
        }
        if (!currentPlayer().equals(secondPlayer)) {
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

        board.placeStonePieRule(row, col, secondPlayer);

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
        board.placeStone(move);
        escortEngine.applyRules(move);

        turn++;
        return true;
    }

    public boolean winBoard() {

         if (goalWinner(firstPlayer)) return true;
         return goalWinner(secondPlayer);

    }

    private boolean goalWinner(Player player)
    {
        int rows = board().getRows();
        int cols = board().getCols();

        boolean[][] visited = new  boolean[rows][cols];
        java.util.ArrayDeque<int[]> queue = new java.util.ArrayDeque<>();

        if(player.equals(firstPlayer) ){
            for (int col = 0; col < cols; col++) {
                if (board.getPlayerAt(0, col).equals(player)) {
                queue.push(new int[]{0, col});
                visited[0][col] = true;
                }
            }
        }
        else {
            for (int row = 0; row < rows; row++) {
                if ( board.getPlayerAt(row, 0).equals(player)) {
                    queue.push(new int[]{row, 0});
                    visited[row][0] = true;
                }
            }
        }



        while (!queue.isEmpty()) {
            int[] cell = queue.pop();
            int row = cell[0];
            int col = cell[1];

            if(board.boundsWithin(row, col) &&
                    ( ( col == board.getCols()-1) && player.equals(secondPlayer)
                    || (row == board.getRows()-1) && player.equals(firstPlayer) )) {
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
