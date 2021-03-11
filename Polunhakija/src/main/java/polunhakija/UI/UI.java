
package polunhakija.UI;

import java.util.Scanner;
import polunhakija.utils.BenchmarkScenario;

/**
 * Commandline UI for the application
 * 
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
    
    /**
     * runs the benchmarks specified for the map
     */
    private void runBenchmark() {
        System.out.println("-----------");
        BenchmarkScenario benchmark = new BenchmarkScenario();
        benchmark.testScenario(mapName, 0, 500);
    }
    
    
    /**
     * opens a Window for visualising the map + path
     */
    private void visualize() {
        Visualizer visualizer = new Visualizer();
        visualizer.setUp(mapName);
        visualizer.update(visualizer.getMap());
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
