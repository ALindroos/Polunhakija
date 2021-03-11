package polunhakija;

import polunhakija.UI.UI;
import polunhakija.utils.MapLoader;

public class Main {
    
    public static void main(String[] args) {
        /*
        UI ui = new UI();
        ui.run();
        */
        
        MapLoader mapLoader = new MapLoader();
        Node[][] map = mapLoader.loadMap("test2.map");
        
        Node start = new Node(5,5);
        Node goal = new Node(5,1);
        
        JPS jps = new JPS();
        double j = jps.findPath(map, start, goal);
        
        System.out.println(j);
        
    }   
}
