package polunhakija;

import javax.swing.JFrame;


public class Main {
    
    public static void main(String[] args) {
        
        MapLoader mapLoader = new MapLoader();
        Node[][] map = mapLoader.loadMap();   
 
        Node start = new Node(29, 10);
        Node goal = new Node(45, 80);
        
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.findPath(map, start, goal);
    }   
}
