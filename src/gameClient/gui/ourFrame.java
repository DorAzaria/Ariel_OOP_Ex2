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
    Range2Range range;

    public ourFrame(String name) {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void update(Arena ar) {
        ManageGame = ar;
        updateFrame();
        initPanel();
    }

    public void updateFrame() {
        Range rx = new Range(20,this.getWidth()-20);
        Range ry = new Range(this.getHeight()-10,150);
        Range2D frame = new Range2D(rx,ry);
        directed_weighted_graph g = ManageGame.getGraph();
        range = Arena.w2f(g,frame);
    }
    public void initPanel() {
        ourPanel panel = new ourPanel(ManageGame,range);
        this.add(panel);
    }

}

