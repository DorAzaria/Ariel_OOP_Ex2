package gameClient.gui;

import api.DWGraph_DS;
import api.*;
import gameClient.Arena;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[]args) throws FileNotFoundException {
        directed_weighted_graph dwg = new DWGraph_DS();
        dw_graph_algorithms dga = new DWGraph_Algo();
        dga.load("A0");
    

    }
}
