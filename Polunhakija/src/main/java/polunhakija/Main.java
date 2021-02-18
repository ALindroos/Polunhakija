package polunhakija;

import javax.swing.JFrame;


public class Main {
    
    public static void main(String[] args) {
        
        String mapName = "";
        if (args.length > 0) {
            mapName = args[0];
        }
        
        
        /*
        MapLoader mapLoader = new MapLoader();
        Node[][] map1 = mapLoader.loadMap(mapName);
        Node[][] map2 = mapLoader.loadMap(mapName);
 
        Node start = new Node(50, 50);
        Node goal = new Node(49, 51);
        
        
        Dijkstra dijkstra = new Dijkstra();
        double d = dijkstra.findPath(map1, start, goal);
        
        JPS jps = new JPS();
        double j = jps.findPath(map2, start, goal);
        
        
        System.out.println("-----------------");
        System.out.println("Dijkstra: " + d);
        System.out.println("JPS: " + j);
        //dijkstra.printState();
        jps.printState();
        */
        
        BenchmarkScenario benchmark = new BenchmarkScenario();
        benchmark.testScenario("AR0011SR.map", 0,10);
        
        
        //visual
        
        /*
        GridCanvas canvas = new GridCanvas(map1.length, map1[0].length);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(canvas);
        frame.pack();
        canvas.updateState(map2);
        canvas.repaint();
        */
        
        
        
        
        
        
    }   
}
