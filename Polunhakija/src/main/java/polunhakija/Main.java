package polunhakija;


public class Main {
    
    public static void main(String[] args) {
        
        String mapName = "";
        if (args.length > 0) {
            mapName = args[0];
        }
        
        MapLoader mapLoader = new MapLoader();
        Node[][] map1 = mapLoader.loadMap(mapName);
        Node[][] map2 = mapLoader.loadMap(mapName);
 
        Node start = new Node(40, 30);
        Node goal = new Node(45, 80);
        
        //Dijkstra dijkstra = new Dijkstra();
        //double d = dijkstra.findPath(map1, start, goal);
        
        JPS jps = new JPS();
        double j = jps.findPath(map2, start, goal);
        
        //dijkstra.printState();
        jps.printState();
        
        
    }   
}
