package brique.view;

import brique.controller.GameController;
import brique.model.Move;
import brique.model.Player;
import brique.model.UnadmissibleMove;

import javax.swing.*;
import java.awt.Color;

class GuiGameController {

    private final GameController core;
    private BoardPanel boardPanel;
    private JLabel lblBlack, lblWhite;
    private final JLabel status = new JLabel();
    private boolean pieAsked  = false;
    private int lastSeenTurn  = 1;

    GuiGameController(GameController core) {
        this.core = core;
        status.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        updateStatus();
    }

    void setBoardPanel(BoardPanel bp)        { boardPanel = bp; }
    void setHeaderLabels(JLabel b, JLabel w){ lblBlack = b; lblWhite = w; }
    JLabel getStatusBar()                    { return status; }

    void handleClick(int row, int col) {
        if (core.currentTurn() != lastSeenTurn) return;
        Player mover = core.currentPlayer();
        try {
            core.makeMove(new Move(row, col, mover, false));
            boardPanel.refresh();
            updateStatus();
            checkWin(mover);
            lastSeenTurn = core.currentTurn();
        } catch (UnadmissibleMove ex) {
            JOptionPane.showMessageDialog(boardPanel, ex.getMessage(),
                    "Invalid Move", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void updateStatus() {
        status.setText("Turn " + core.currentTurn() + " – Playing: " + core.currentPlayer().name());
        askPieRule();
        updateHeader();
    }

    private void askPieRule() {
        if (pieAsked || !core.pieRuleAvailable()) return;
        int ok = JOptionPane.showConfirmDialog(boardPanel,
                "Do you want to activate the Pie Rule and take the first stone?",
                "Pie Rule", JOptionPane.YES_NO_OPTION);
        pieAsked = true;
        if (ok != JOptionPane.YES_OPTION) return;

        try {
            core.applyPieMove();
            boardPanel.refresh();
            updateStatus();
            checkWin(core.getFirstPlayer());
            lastSeenTurn = core.currentTurn();
        } catch (UnadmissibleMove ex) {
            JOptionPane.showMessageDialog(boardPanel, ex.getMessage(),
                    "Pie Rule Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkWin(Player mover) {
        if (core.winBoard()) {
            JOptionPane.showMessageDialog(boardPanel, mover.name() + " wins!",
                    "Game Over", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    Color colorOf(Player p) {
        return core.isBlack(p) ? Color.BLACK : Color.WHITE;
    }

    private void updateHeader() {
        if (lblBlack == null) return;
        lblBlack.setText("● " + core.getFirstPlayer().name());
        lblWhite.setText("○ " + core.getSecondPlayer().name());
    }
}
