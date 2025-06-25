package brique.model;

public class EscortRuleEngine {
    private final Board board;

    public EscortRuleEngine(Board board) {
        this.board = board;
    }

    public void applyRules(Move move) {
        int row = move.getRow();
        int col = move.getCol();
        Player player = move.getPlayer();


        checkNorthEastEscortRule(row-1, col+1, player);
        checkSouthWestEscortRule(row+1, col-1, player);
    }

    private void checkNorthEastEscortRule(int targetRow, int targetCol, Player player) {
        if(!board.boundsWithin(targetRow, targetCol)) return;

        Player cellPlayer = board.getPlayerAt(targetRow, targetCol);
        if ((targetRow + targetCol) % 2 == 0 && player.equals(cellPlayer)){
            board.placeStoneEscortRule(new Move(targetRow , targetCol-1, player));
        }
        else if ((targetRow + targetCol) % 2 == 1 && player.equals(cellPlayer)){
            board.placeStoneEscortRule(new Move(targetRow + 1, targetCol, player));
        }

    }
    private void checkSouthWestEscortRule(int targetRow, int targetCol, Player player) {
        if(!board.boundsWithin(targetRow, targetCol)) return;

        Player cellPlayer = board.getPlayerAt(targetRow, targetCol);
        if ((targetRow + targetCol) % 2 == 0 && player.equals(cellPlayer)){
            board.placeStoneEscortRule(new Move(targetRow - 1, targetCol, player));
        }
        else if ((targetRow + targetCol) % 2 == 1 && player.equals(cellPlayer)){
            board.placeStoneEscortRule(new Move(targetRow, targetCol + 1, player));
        }

    }
}
