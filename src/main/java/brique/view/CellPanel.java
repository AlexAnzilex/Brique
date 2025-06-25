package brique.view;

import brique.model.Board;
import brique.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class CellPanel extends JPanel {

    private static final Color LIGHT = new Color(0xD6D1E4);
    private static final Color DARK  = new Color(0xB3A8C4);

    private final int row, col;
    private final Board board;
    private final GuiGameController ctrl;

    CellPanel(int row, int col, Board board, GuiGameController ctrl) {
        this.row = row;
        this.col = col;
        this.board = board;
        this.ctrl  = ctrl;

        setOpaque(true);
        setPreferredSize(new Dimension(36, 36));

        addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) ctrl.handleClick(row, col);
            }
        });
    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(((row + col) & 1) == 0 ? LIGHT : DARK);
        g.fillRect(0, 0, getWidth(), getHeight());

        Player p = board.getPlayerAt(row, col);
        if (!p.name().equals("None")) {
            g.setColor(ctrl.colorOf(p));
            int m = (int) (getWidth() * 0.15);
            g.fillOval(m, m, getWidth() - 2 * m, getHeight() - 2 * m);
            g.setColor(Color.DARK_GRAY);
            g.drawOval(m, m, getWidth() - 2 * m, getHeight() - 2 * m);
        }
    }
}
