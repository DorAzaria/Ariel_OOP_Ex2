package gameClient.gui;
import javax.swing.*;
import java.awt.*;

public class ourFrame extends JFrame {

    public ourFrame() {
        initFrame();
        initPanel();
    }

    private void initPanel() {
    ourPanel panel = new ourPanel();
    this.add(panel);
    }

    private void initFrame() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(true);
    setSize(300,300);
    setVisible(true);
    }

}
