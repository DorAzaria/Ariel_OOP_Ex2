package gameClient.gui;
import api.directed_weighted_graph;
import gameClient.Arena;
import gameClient.util.Range;
import gameClient.util.Range2D;

import javax.swing.*;
import java.awt.*;

public class ourFrame extends JFrame {
    private int _ind;
    private Arena _ar;
    private gameClient.util.Range2Range _w2f;
    ourPanel panel;

    ourFrame(String a) {
        super(a);
        int _ind = 0;
        panel = new ourPanel(_ar);
    }
    public void update(Arena ar) {
        this._ar = ar;
        updateFrame();
    }

    private void updateFrame() {
        Range rx = new Range(20,this.getWidth()-20);
        Range ry = new Range(this.getHeight()-10,150);
        Range2D frame = new Range2D(rx,ry);
        directed_weighted_graph g = _ar.getGraph();
        _w2f = Arena.w2f(g,frame);
    }
}
