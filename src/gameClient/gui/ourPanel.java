package gameClient.gui;

import Server.Game_Server_Ex2;
import api.directed_weighted_graph;
import api.edge_data;
import api.geo_location;
import api.node_data;
import gameClient.Agent;
import gameClient.Arena;
import gameClient.Ex2;
import gameClient.Pokemon;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;
import gameClient.util.Range2Range;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ourPanel extends JPanel {
    Arena ManageGame;
    Range2Range range;
    final int r = 7;
    double grade = 0;
    int moves = 0;

    public ourPanel(Arena arena) {
        ManageGame = arena;
    }
    public void paint(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();
        resize();
        g.setColor(new Color(43,43,43));
        g.fillRect(0,0,width,height);
        drawGraph((Graphics2D)g);
        drawPokemons((Graphics2D)g);
        drawAgants((Graphics2D)g);
        g.drawString("Time: "+ManageGame.getGame().timeToEnd(),10,30);
        g.drawString("Grade: "+grade,10,50);
        g.drawString("Moves: "+moves++,10,70);

    }

    private void resize() {
        Range rx = new Range(50,this.getWidth()-50);
        Range ry = new Range(this.getHeight()-20,100);
        Range2D frame = new Range2D(rx,ry);
        directed_weighted_graph graph = ManageGame.getGraph();
        range = Arena.w2f(graph,frame);
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
                    try {
                        BufferedImage img = ImageIO.read(new File("resource/pikachu.gif"));
                        g.drawImage(img, (int)fp.x()-30, (int)fp.y()-30, 7*r, 5*r, this);
                        g.drawString("Pikachu", (int)fp.x()-10, (int)fp.y()-8*r);
                        g.drawString("Value:"+f.getValue(), (int)fp.x()-13, (int)fp.y()-6*r);

                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    private void drawAgants(Graphics2D g) {
        List<Agent> rs = ManageGame.getAgents();
        int i=0;
        while(rs != null && i < rs.size()) {
            geo_location c = rs.get(i).getLocation();
            String value = String.valueOf(rs.get(i).getKey());
            if(grade < rs.get(i).getKey() ) {
                grade += rs.get(i).getKey();
            }
            int r=8;
            i++;
            if(c!=null) {

                geo_location fp = this.range.world2frame(c);
                try {
                    BufferedImage img = ImageIO.read(new File("resource/ash.gif"));
                    g.drawImage(img, (int)fp.x()-30, (int)fp.y()-30, 5*r, 5*r, this);
                    g.drawString("Ash", (int)fp.x()-20, (int)fp.y()-8*r);
                    g.drawString("Points:"+value, (int)fp.x()-30, (int)fp.y()-6*r);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
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
        try {
            BufferedImage pokador = ImageIO.read(new File("resource/pokador.png"));
            g.drawImage(pokador, (int)fp.x()-30, (int)fp.y()-30, 4*r, 5*r, this);
            g.setFont(new Font("Segoe UI",Font.PLAIN,20));
            g.drawString(" "+n.getKey(), (int)fp.x()-25, (int)fp.y()-40);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }
}
