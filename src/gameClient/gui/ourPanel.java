package gameClient.gui;
import api.*;
import gameClient.*;
import gameClient.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ourPanel extends JPanel {
    Arena ManageGame;
    Range2Range range;
    final int r = 7;
    double grade = 0;
    int moves = 0;

    public ourPanel(Arena arena) {
        ManageGame = arena;
        importPictures();
    }

    public void paint(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();
        resize();
        g.drawImage(background, 0,0,width,height, this);
        drawGraph((Graphics2D)g);
        drawPokemon((Graphics2D)g);
        drawAgents((Graphics2D)g);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(ManageGame.getGame().timeToEnd());
        g.setColor(Color.black);
        g.drawImage(blur,-5, 0,170,150,this);
        g.drawImage(logo, 5,0,150,80, this);
        g.setColor(Color.white);
        g.drawString("Time: 00:"+seconds,25,90);
        g.drawString("Grade: "+grade,25,110);
        g.drawString("Moves: "+moves++,25,130);
    }

    private void resize() {
        Range rx = new Range(120,this.getWidth()-120);
        Range ry = new Range(this.getHeight()-70,170);
        Range2D frame = new Range2D(rx,ry);
        directed_weighted_graph graph = ManageGame.getGraph();
        range = Arena.w2f(graph,frame);
    }

    private void drawPokemon(Graphics2D g) {
        java.util.List<Pokemon> fs = ManageGame.getPokemons();
        if(fs!=null) {

            for (Pokemon f : fs) {
                Point3D c = f.getLocation();
                Color color = Color.green;
                if (f.getType() < 0) {
                    color = Color.orange;
                }
                if (c != null) {
                    geo_location fp = this.range.world2frame(c);
                    if (f.getValue() < 6) {
                        pikachu(g, fp, f, color);
                    } else if (f.getValue() < 11) {
                        charizard(g, fp, f, color);
                    } else {
                        mewtwo(g, fp, f, color);
                    }
                }
            }
        }
    }
    private void drawAgents(Graphics2D g) {
        List<Agent> rs = ManageGame.getAgents();
        int i = 0;
        while(rs != null && i < rs.size()) {
            geo_location agent_location = rs.get(i).getLocation();
            String value = String.valueOf(rs.get(i).getKey());
            if(grade < rs.get(i).getKey() ) {
                grade += rs.get(i).getKey();
            }
            int r = 8 ;
            i++;
            if(agent_location != null) {
                geo_location fp = this.range.world2frame(agent_location);
                g.drawImage(ash, (int)fp.x()-30, (int)fp.y()-30, 5*r, 5*r, this);
                g.drawImage(blur,(int)fp.x()-55, (int)fp.y()-85,120,50,this);
                g.setColor(Color.RED);
                g.drawString("Ash", (int)fp.x()-20, (int)fp.y()-8*r);
                g.setColor(Color.WHITE);
                g.drawString("Points:"+value, (int)fp.x()-45, (int)fp.y()-6*r);
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
            drawNode(runner, g);
        }
    }

    private void drawNode(node_data n, Graphics2D g) {
        g.setColor(new Color(73,155,84));
        geo_location pos = n.getLocation();
        geo_location fp = this.range.world2frame(pos);
        g.drawImage(pokador, (int)fp.x()-15, (int)fp.y()-30, 4*r, 5*r, this);
        g.setFont(new Font("Segoe UI",Font.BOLD,20));
        g.setColor(Color.black);
        g.drawString(" "+n.getKey(), (int)fp.x()-14, (int)fp.y()-34);
    }

    private void drawEdge(edge_data e, Graphics2D g) {
        directed_weighted_graph gg = ManageGame.getGraph();
        geo_location s = gg.getNode(e.getSrc()).getLocation();
        geo_location d = gg.getNode(e.getDest()).getLocation();
        geo_location s0 = this.range.world2frame(s);
        geo_location d0 = this.range.world2frame(d);
        g.setColor(new Color(173, 122, 68));
        g.setStroke(new BasicStroke(10));
        g.drawLine((int)s0.x(), (int)s0.y(), (int)d0.x(), (int)d0.y());


    }

    private void pikachu(Graphics g, geo_location fp, Pokemon f, Color color) {
        g.drawImage(pikachu, (int)fp.x()-30, (int)fp.y()-30, 8*r, 5*r, this);
        g.drawImage(blur,(int)fp.x()-36, (int)fp.y()-78,120,50,this);
        g.setColor(color);
        g.drawString("Pikachu", (int)fp.x()-12, (int)fp.y()-60);
        g.setColor(Color.WHITE);
        g.drawString("Value:"+f.getValue(), (int)fp.x()-18, (int)fp.y()-39);
    }
    private void charizard(Graphics g, geo_location fp, Pokemon f, Color color) {
        g.drawImage(charizard, (int)fp.x()-30, (int)fp.y()-30, 8*r, 8*r, this);
        g.drawImage(blur,(int)fp.x()-65, (int)fp.y()-12*r,130,50,this);
        g.setColor(color);
        g.drawString("Charizard", (int)fp.x()-45, (int)fp.y()-9*r);
        g.setColor(Color.WHITE);
        g.drawString("Value:"+f.getValue(), (int)fp.x()-42, (int)fp.y()-6*r);
    }
    private void mewtwo(Graphics g, geo_location fp, Pokemon f, Color color) {
        g.drawImage(mewtwo, (int)fp.x()-40, (int)fp.y()-30, 8*r, 8*r, this);
        g.drawImage(blur,(int)fp.x()-60, (int)fp.y()-78,130,50,this);
        g.setColor(color);
        g.drawString("Mewtwo", (int)fp.x()-35, (int)fp.y()-58);
        g.setColor(Color.WHITE);
        g.drawString("Value:"+f.getValue(), (int)fp.x()-42, (int)fp.y()-38);
    }

    ///// import pictures /////
    static BufferedImage background = null;
    static BufferedImage logo = null;
    static BufferedImage blur = null;
    static BufferedImage ash = null;
    static BufferedImage pokador = null;
    static BufferedImage pikachu = null;
    static BufferedImage charizard = null;
    static BufferedImage mewtwo = null;
    public static void importPictures() {
        try {
            background = ImageIO.read(new File("resource/background.jpg"));
            logo = ImageIO.read(new File("resource/logo.png"));
            blur = ImageIO.read(new File("resource/blur.png"));
            ash = ImageIO.read(new File("resource/ash.gif"));
            pokador = ImageIO.read(new File("resource/pokador.png"));
            pikachu = ImageIO.read(new File("resource/pikachu.gif"));
            charizard = ImageIO.read(new File("resource/charizard.png"));
            mewtwo = ImageIO.read(new File("resource/mewtwo.png"));
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
