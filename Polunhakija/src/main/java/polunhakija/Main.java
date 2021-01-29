package polunhakija;


public class Main {
    
    public static void main(String[] args) {
        MapLoader mapLoader = new MapLoader();
        int[][] map = mapLoader.loadMap();
        
        map[29][10] = 5;
        map[45][84] = 4;
        
        for (int y=0; y<map[0].length; y++) {
            for (int x=0; x<map.length; x++) {
                System.out.print(map[x][y]);
            }
            System.out.println();
        }
        
        
    }   
}
