
package polunhakija;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class DijkstraTest {
    
    private Dijkstra dijkstra;
    private Node[][] map;
    
    @Before
    public void setUp() {
        map = new Node[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Node n = new Node(i,j);
                if (i == 0 || i == 6 || j == 6 | j == 0) {
                    n.wall = true;
                }
                map[i][j] = n;
            }
        }
        map[3][4].wall = true;
        map[4][4].wall = true;
        map[5][4].wall = true;
        
        dijkstra = new Dijkstra();
    }
    
    @Test public void findsCorrectPath() {
        Node start = new Node(5,5);
        Node goal = new Node(5,1);
        
        double d = dijkstra.findPath(map, start, goal);
        
        assertEquals(d, 8.8, 0.1);        
    }   
}
