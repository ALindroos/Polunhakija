package polunhakija;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class MapLoader {
    
    public int[][] loadMap() {
       
        String regex = "[^\\d]+";
        try (Scanner scanner = new Scanner(Paths.get("AR0015SR.map"))) {                        
            List<String> allLines = Files.readAllLines(Paths.get("AR0015SR.map"));
            int height_y = Integer.parseInt(allLines.get(1).split(regex)[1]);
            int width_x = Integer.parseInt(allLines.get(2).split(regex)[1]);            
            
            int[][] map = new int[width_x][height_y];                     
            
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); 
                if (i > 4) {
                    for (int x = 0; x < width_x; x++) {                       
                        if (line.charAt(x) == '@' ) {
                            map[x][i-4] = 0;
                        } else {
                            map[x][i-4] = 1;
                        }
                    }
                }                
                i++;
            }   
            return map;
            
        } catch (Exception e) {
            System.out.println("Failure loading map data");
            System.out.println(e);
        }
        
        return null;
    }
}
