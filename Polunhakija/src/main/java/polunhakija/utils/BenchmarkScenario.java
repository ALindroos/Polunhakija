/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polunhakija.utils;

import java.nio.file.Paths;
import java.util.Scanner;
import polunhakija.Dijkstra;
import polunhakija.JPS;
import polunhakija.Node;

/**
 * runs benchmarks for the map specified in the .scen file
 * 
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
            double dijkstraTotalTime = 0;
            double jpsTotalTime = 0;
            double jpsPathDiff = 0;
            double optPathTotal = 0;
            double threshold = 0.01;
            double jpsMaxDiff = 0;
            
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
                
                
                if (Math.abs(optimal - d) < threshold && Math.abs(optimal - j) < threshold) {
                    //System.out.println("Test " + i +  " OK!");
                    //System.out.println("Length: " + optimal);
                    //System.out.println("-----------");
                    correct++;
                } else {
                    System.out.println("Missmatch! Test " + i);
                    System.out.println(start.x + ":" + start.y + " -> " +
                            goal.x + ":" + goal.y);
                    System.out.println("Dijkstra: " + d + " | " + dijkstra.getRunTime() + "ms");
                    System.out.println("JPS: " + j + " | " + jps.getRunTime() + "ms");
                    System.out.println("Optimal: " + optimal);
                    System.out.println("-------------------");
                    jpsPathDiff += (j - optimal);
                }
                
                dijkstraTotalTime += dijkstra.getRunTime();
                jpsTotalTime += jps.getRunTime();
                optPathTotal += optimal;
                jpsMaxDiff = Math.max(jpsMaxDiff, (j - optimal));
                
                i++;
            }
            
            i--;
            double dijkstraAvgTime = dijkstraTotalTime / i;
            double jpsAvgTime = jpsTotalTime / i;
            double avgPath = optPathTotal / i;
            double avgJPSDiff = jpsPathDiff / (i - correct);
            
            System.out.println(correct + "/" + (i) + " tests succesful");
            System.out.println("Average path length (optimal): " + avgPath);
            System.out.println("Average path diff for JPS: " + avgJPSDiff);
            System.out.println("Maximum path diff for JPS: " + jpsMaxDiff);
            System.out.println("Average runtime for Dijkstra: " + dijkstraAvgTime + "ms");
            System.out.println("Average runtime for JPS: " + jpsAvgTime + "ms");
            
            
        } catch (Exception e) {
            System.out.println(e); 
        }
        
        
        
    }
    
}
