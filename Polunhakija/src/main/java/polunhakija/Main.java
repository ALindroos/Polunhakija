package polunhakija;


public class Main {
    
    public static void main(String[] args) {
        
        MapLoader mapLoader = new MapLoader();
        Node[][] map = mapLoader.loadMap();   
 
        Node start = new Node(40, 30);
        Node goal = new Node(45, 80);
        //Dijkstra dijkstra = new Dijkstra();
        //dijkstra.findPath(map, start, goal);
        
        JPS jps = new JPS();
        jps.findPath(map, start, goal);
        
    }   
}
