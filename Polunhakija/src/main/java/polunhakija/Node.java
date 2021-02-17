
package polunhakija;

/**
 * Class for representing nodes in the graph 
 *
 */

public class Node {
    
    int x;
    int y;
    double distance;
    Node parent;
    boolean visited;
    boolean path;
    boolean wall;
    int cost;
    boolean jmp;
    
    
    /**
     * 
     * @param x X-coordinate of the Node
     * @param y Y-coordinate
     * distance is initialised as large as possible to make distance comparisons easier 
     */
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.distance = Integer.MAX_VALUE;
    }
}
