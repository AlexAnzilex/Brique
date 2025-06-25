package brique.view;

import brique.model.Board;

import javax.swing.*;
import java.awt.*;

class BoardPanel extends JPanel {

    private static final int CELL = 36;

    BoardPanel(Board board, GuiGameController ctrl) {
        setLayout(new GridLayout(board.getRows(), board.getCols()));
        for (int r = 0; r < board.getRows(); r++)
            for (int c = 0; c < board.getCols(); c++)
                add(new CellPanel(r, c, board, ctrl));
    }

    @Override public Dimension getPreferredSize() {
        GridLayout g = (GridLayout) getLayout();
        return new Dimension(g.getColumns() * CELL, g.getRows() * CELL);
    }

    void refresh() {
        revalidate();
        repaint();
        paintImmediately(getBounds());
    }
}
