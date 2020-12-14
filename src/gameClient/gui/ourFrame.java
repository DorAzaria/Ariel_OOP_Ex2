package gameClient.gui;
import gameClient.*;
import javax.swing.*;

public class ourFrame extends JFrame {
    Arena ManageGame;

    public ourFrame() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void update(Arena ar) {
        ManageGame = ar;
        initPanel();
    }

    public void initPanel() {
        ourPanel panel = new ourPanel(ManageGame);
        this.add(panel);
    }

}