package brique;

import brique.view.BriqueFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BriqueFrame().setVisible(true));
    }
}
