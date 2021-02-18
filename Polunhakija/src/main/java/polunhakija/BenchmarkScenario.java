/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polunhakija;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author lindradi
 */
public class BenchmarkScenario {
    
    
    public void testScenario(String mapname, int groupLower, int groupUpper) {
    
        Dijkstra dijkstra = new Dijkstra();
        JPS jps = new JPS();
        MapLoader maploader = new MapLoader();
        Node[][] map = maploader.loadMap(mapname);
        
        try (Scanner scanner = new Scanner(Paths.get(mapname + ".scen"))) {

            int i = 0;
            int correct = 0;
            
            while (scanner.hasNextLine()) {
                
                String[] fields = scanner.nextLine().split("\\s+");
                
                //skip first line
                if ( i == 0) {
                    i++;
                    continue;
                } 
                
                //limit benchmark to groups between lower & upper bounds
                if (Integer.parseInt(fields[0]) < groupLower) {
                    continue;
                } 
                if (Integer.parseInt(fields[0]) > groupUpper) {
                    break;
                }
                
                Node start = new Node(Integer.parseInt(fields[4]), Integer.parseInt(fields[5]));
                Node goal = new Node(Integer.parseInt(fields[6]), Integer.parseInt(fields[7]));
                double optimal = Double.parseDouble(fields[8]);
                
                Node[][] mapD = maploader.loadMap(mapname);
                Node[][] mapJ = maploader.loadMap(mapname);
                
                double d = dijkstra.findPath(mapD, start, goal);
                double j = jps.findPath(mapJ, start, goal);
                
                
                if (Math.abs(optimal - d) < 0.01 && Math.abs(optimal - j) < 0.01) {
                    //System.out.println("Test " + i +  " OK!");
                    //System.out.println("Length: " + optimal);
                    //System.out.println("-----------");
                    correct++;
                } else {
                    System.out.println("Missmatch! Test " + i);
                    System.out.println("Dijkstra: " + d);
                    System.out.println("JPS: " + j);
                    System.out.println("Optimal: " + optimal);
                    System.out.println("-------------------");
                }
                
                i++;
            }
            
            System.out.println(correct + "/" + i + " tests succesful");
            
            
        } catch (Exception e) {
            System.out.println(e); 
        }
        
        
        
    }
    
}
