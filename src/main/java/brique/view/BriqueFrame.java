package brique.view;

import brique.controller.GameController;
import brique.model.Player;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class BriqueFrame extends JFrame {

    public BriqueFrame() {
        String nBlack = JOptionPane.showInputDialog(
                this, "Black player's name:", "Players", JOptionPane.QUESTION_MESSAGE);
        if (nBlack == null) System.exit(0);
        nBlack = nBlack.isBlank() ? "Black" : nBlack.trim();

        String nWhite;
        while (true) {
            nWhite = JOptionPane.showInputDialog(
                    this, "White player's name:", "Players", JOptionPane.QUESTION_MESSAGE);
            if (nWhite == null) System.exit(0);
            nWhite = nWhite.isBlank() ? "White" : nWhite.trim();
            if (!nWhite.equalsIgnoreCase(nBlack)) break;
            JOptionPane.showMessageDialog(this,
                    "The two players must have different names.",
                    "Duplicate name", JOptionPane.ERROR_MESSAGE);
        }

        Player pBlack = new Player(nBlack);
        Player pWhite = new Player(nWhite);

        GameController core = new GameController(pBlack, pWhite);
        GuiGameController gui = new GuiGameController(core);

        BoardPanel board = new BoardPanel(core.board(), gui);
        gui.setBoardPanel(board);

        JLabel lblBlack = playerLabel(pBlack.name(), true);
        JLabel lblWhite = playerLabel(pWhite.name(), false);
        gui.setHeaderLabels(lblBlack, lblWhite);

        JPanel header = new JPanel(new GridLayout(1, 2));
        header.add(lblBlack);
        header.add(lblWhite);
        header.setBackground(new Color(0xD0D0D0));

        Border tb = BorderFactory.createMatteBorder(6, 0, 6, 0, Color.BLACK);
        Border lr = BorderFactory.createMatteBorder(0, 6, 0, 6, Color.WHITE);
        board.setBorder(new CompoundBorder(tb, lr));

        setTitle("Brique");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);
        add(gui.getStatusBar(), BorderLayout.SOUTH);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private static JLabel playerLabel(String name, boolean black) {
        String bullet = black ? "●" : "○ ";
        JLabel lbl = new JLabel(bullet + " " + name, JLabel.CENTER);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 14f));
        lbl.setForeground(Color.BLACK);
        lbl.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        return lbl;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BriqueFrame().setVisible(true));
    }
}