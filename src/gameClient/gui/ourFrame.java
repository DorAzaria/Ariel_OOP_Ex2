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
    final int r = 5; // im study at ariel university and they teach me to think like parrot

    public ourFrame(String name) {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void updateFrame() {
        Range rx = new Range(20,this.getWidth()-20);
        Range ry = new Range(this.getHeight()-10,150);
        Range2D frame = new Range2D(rx,ry);
        directed_weighted_graph g = ManageGame.getGraph();
        range = Arena.w2f(g,frame);
    }

   public void Paint(Graphics g) {
       int width = this.getWidth();
       int height = this.getHeight();
       drawGraph(g);
//       drawPokemons(g);
//       drawAgants(g);
//       drawInfo(g);
   }

    private void drawInfo(Graphics g) {
        java.util.List<String> str = ManageGame.get_info();
        String dt = "none";
        for(int i=0;i<str.size();i++) {
            g.drawString(str.get(i)+" dt: "+dt,100,60+i*20);
        }

    }
    private void drawPokemons(Graphics g) {
        java.util.List<CL_Pokemon> fs = ManageGame.getPokemons();
        if(fs!=null) {
            Iterator<CL_Pokemon> itr = fs.iterator();

            while(itr.hasNext()) {

                CL_Pokemon f = itr.next();
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
    private void drawAgants(Graphics g) {
        List<CL_Agent> rs = ManageGame.getAgents();
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


    private void drawGraph(Graphics g) {
        directed_weighted_graph graph = ManageGame.getGraph();
        for(node_data runner : graph.getV()) {
            drawNode(runner,r,g);
        }
        for(edge_data edge : ((DWGraph_DS)graph).edges) {
            drawEdge(edge,g);
        }
    }

    private void drawNode(node_data n, int r, Graphics g) {
        g.setColor(new Color(73,155,84));
        geo_location pos = n.getLocation();
        geo_location fp = this.range.world2frame(pos);
        g.fillOval((int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r);
        g.drawString(""+n.getKey(), (int)fp.x(), (int)fp.y()-4*r);
    }
    private void drawEdge(edge_data e, Graphics g) {
        geo_location source_geo = ((Edges)e).getSource().getLocation();
        geo_location destination_geo = ((Edges)e).getDestination().getLocation();
        geo_location s0 = this.range.world2frame(source_geo);
        geo_location d0 = this.range.world2frame(destination_geo);
        g.drawLine((int)s0.x(), (int)s0.y(), (int)d0.x(), (int)d0.y());
    }


}
