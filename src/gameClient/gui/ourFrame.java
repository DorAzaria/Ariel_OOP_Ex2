package gameClient.gui;
import api.*;
import gameClient.*;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;
import gameClient.util.Range2Range;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class ourFrame extends JFrame {
    Arena ManageGame;

    public ourFrame(String name) {
        super(name);
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

