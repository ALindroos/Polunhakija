package polunhakija.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import polunhakija.Node;

/**
 * 
 * class for loading map files and converting them to grid representation of a graph
 */
public class MapLoader {
    
    
    /**
     * 
     *
     * @param mapName filename for map to be loaded
     * loadable maps should be put to src folder
     * @return returns a 2D array of Nodes witch is used the graph
     */
    public Node[][] loadMap(String mapName) {
       
        //AR0015SR
        if (mapName.isEmpty()) {
            mapName = "AR0011SR.map";
        }
        
        String regex = "[^\\d]+";
        try (Scanner scanner = new Scanner(Paths.get(mapName))) {                        
            List<String> allLines = Files.readAllLines(Paths.get(mapName));
            int height_y = Integer.parseInt(allLines.get(1).split(regex)[1]);
            int width_x = Integer.parseInt(allLines.get(2).split(regex)[1]);            
            
            
            int[][] map = new int[width_x][height_y];    
            Node[][] nodeMap = new Node[width_x][height_y];
            
            int y = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); 
                if (y > 3) {
                    for (int x = 0; x < width_x; x++) { 
                        nodeMap[x][y-4] = new Node(x, y-4);
                        if (line.charAt(x) == '@' ) {
                            nodeMap[x][y-4].wall = true;
                        } else {
                            nodeMap[x][y-4].cost = 1;
                        }
                    }
                }                
                y++;
            }   
            
            return nodeMap;
            
        } catch (Exception e) {
            System.out.println("Failure loading map data");
            System.out.println(e);
        }
        
        return null;
    }
}
