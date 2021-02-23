
package polunhakija;

import java.util.Scanner;

/**
 *
 * @author lindradi
 */
public class UI {
    
    private Scanner scanner;
    private String mapName;
    
    public void run() {
        scanner = new Scanner(System.in);
        initUI();
    } 
    
    private void initUI() {
        System.out.println("###########");
        System.out.println("Polunhakija");
        System.out.println("###########");
        System.out.println("Input map name: (ex. AR0015SR.map)");
        mapName = scanner.nextLine();
        showMenu();
    }
    
    private void changeMap() {
        System.out.println("Input new map name:");
        mapName = scanner.nextLine();
    }
    
    private void runBenchmark() {
        System.out.println("-----------");
        BenchmarkScenario benchmark = new BenchmarkScenario();
        benchmark.testScenario(mapName, 0, 500);
    }
    
    private void visualize() {
        int startX;
        int startY;
        int goalX;
        int goalY;
        
        System.out.println("Give starting x-coordinate:");
        startX = scanner.nextInt();
        System.out.println("Starting y-coordinate");
        startY = scanner.nextInt();
        System.out.println("Goal x-coordinate");
        goalX = scanner.nextInt();
        System.out.println("Goal y-coordinate");
        goalY = scanner.nextInt();
        
        Node start = new Node(startX, startY);
        Node goal = new Node(goalX, goalY);
        
        MapLoader maploader = new MapLoader();
        Node[][] mapD = maploader.loadMap(mapName);
        Node[][] mapJ = maploader.loadMap(mapName);
        
        Dijkstra dijkstra = new Dijkstra();
        JPS jps = new JPS();
        
        dijkstra.findPath(mapD, start, goal);
        jps.findPath(mapJ, start, goal);
        
        Visualizer visualizer = new Visualizer();
        visualizer.setUp(mapJ.length, mapJ[0].length);
        visualizer.update(mapJ);
        
        
        
    }
    
    
    private void showMenu() {
        while (true) {
            System.out.println("---------------");
            System.out.println("Map: " + mapName);
            System.out.println("[R]un benchmarks");
            System.out.println("[V]isualize a Path");
            System.out.println("[C]hange map");
            System.out.println("[Q]uit");
            
            String arg = scanner.nextLine();
            
            if (arg.equalsIgnoreCase("q")) {
                return;
            }
            if (arg.equalsIgnoreCase("r")) {
                runBenchmark();
            }
            if (arg.equalsIgnoreCase("v")) {
                visualize();
            }
            if (arg.equalsIgnoreCase("c")) {
                changeMap();
            }
            
        }
    }
    
    
}
