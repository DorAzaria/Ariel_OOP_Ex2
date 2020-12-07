package gameClient.gui;
import api.*;
import gameClient.util.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;


public class ourPanel extends JPanel {
    dw_graph_algorithms graph_algo;
    directed_weighted_graph graph;
    private Range2Range _w2f;


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph(g);
    }

    public ourPanel() {
        Color p = new Color(43, 43, 43);
        setBackground(p);

    }

   public void drawGraph(Graphics g) {
    graph_algo = new DWGraph_Algo();
    try {
        graph_algo.load("A0");
        graph = graph_algo.getGraph();
        for(node_data current : graph.getV()) {
            drawNode(g, current);
        }
    }
    catch (FileNotFoundException f){
        f.printStackTrace();
    }

   }

   private void drawNode(Graphics g, node_data current) {

        geo_location geo = current.getLocation();
        geo_location p = this._w2f.world2frame(geo);
        g.setColor(new Color(73,155,84));
        g.fillOval((int)geo.x(),(int)geo.y(),20,20);
        g.drawString(""+current.getKey(),(int)geo.x()+50,(int)geo.y()+10);
   }

}
