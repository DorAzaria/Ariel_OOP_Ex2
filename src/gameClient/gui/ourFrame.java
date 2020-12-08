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
    int ind;
    final int r = 7; // im study at ariel university and they teach me to think like parrot

    public ourFrame(String name) {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void update(Arena ar) {
        ManageGame = ar;
        updateFrame();
    }
    public void updateFrame() {
        Range rx = new Range(20,this.getWidth()-20);
        Range ry = new Range(this.getHeight()-10,150);
        Range2D frame = new Range2D(rx,ry);
        directed_weighted_graph g = ManageGame.getGraph();
        range = Arena.w2f(g,frame);
    }

   public void paint(Graphics g) {
       int width = this.getWidth();
       int height = this.getHeight();
       g.clearRect(0, 0, width, height);
       drawGraph((Graphics2D)g);
       drawPokemons((Graphics2D)g);
       drawAgants((Graphics2D)g);
       drawInfo((Graphics2D)g);
   }

    private void drawInfo(Graphics2D g) {
        java.util.List<String> str = ManageGame.getInfo();
        String dt = "none";
        for(int i=0;i<str.size();i++) {
            g.drawString(str.get(i)+" dt: "+dt,100,60+i*20);
        }

    }
    private void drawPokemons(Graphics2D g) {
        java.util.List<Pokemon> fs = ManageGame.getPokemons();
        if(fs!=null) {
            Iterator<Pokemon> itr = fs.iterator();

            while(itr.hasNext()) {

                Pokemon f = itr.next();
                Point3D c = f.getLocation();
                int r=10;
                g.setColor(Color.green);
                if(f.getType()<0) {g.setColor(Color.orange);}
                if(c!=null) {

                    geo_location fp = this.range.world2frame(c);
                    g.fillOval((int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r);
                    //	g.drawString(""+n.getKey(), fp.ix(), fp.iy()-4*r);

                }
            }
        }
    }
    private void drawAgants(Graphics2D g) {
        List<Agent> rs = ManageGame.getAgents();
        //	Iterator<OOP_Point3D> itr = rs.iterator();
        g.setColor(Color.red);
        int i=0;
        while(rs!=null && i<rs.size()) {
            geo_location c = rs.get(i).getLocation();
            int r=8;
            i++;
            if(c!=null) {

                geo_location fp = this.range.world2frame(c);
                g.fillOval((int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r);
            }
        }
    }

    private void drawGraph(Graphics2D g) {
        directed_weighted_graph graph = ManageGame.getGraph();
        for(node_data runner : graph.getV()) {
            for (edge_data edge : graph.getE(runner.getKey())) {
                drawEdge(edge, g);
            }
        }
        for(node_data runner : graph.getV()) {
            drawNode(runner, r, g);
        }
    }

    private void drawNode(node_data n, int r, Graphics2D g) {
        g.setColor(new Color(73,155,84));
        geo_location pos = n.getLocation();
        geo_location fp = this.range.world2frame(pos);
        g.fillOval((int)fp.x()-r, (int)fp.y()-r, 5*r, 5*r);
        g.drawString(""+n.getKey(), (int)fp.x(), (int)fp.y()-4*r);
    }

    private void drawEdge(edge_data e, Graphics2D g) {
        g.setColor(new Color(139, 139, 139));
        g.setStroke(new BasicStroke(5));
        directed_weighted_graph gg = ManageGame.getGraph();
        geo_location s = gg.getNode(e.getSrc()).getLocation();
        geo_location d = gg.getNode(e.getDest()).getLocation();
        geo_location s0 = this.range.world2frame(s);
        geo_location d0 = this.range.world2frame(d);
        g.drawLine((int)s0.x(), (int)s0.y(), (int)d0.x(), (int)d0.y());

        //	g.drawString(""+n.getKey(), fp.ix(), fp.iy()-4*r);
    }
}
