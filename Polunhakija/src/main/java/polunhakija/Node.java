
package polunhakija;

/**
 * Class for representing nodes in the graph 
 *
 */

public class Node {
    
    public int x;
    public int y;
    public double distance;
    public double priority;
    public Node parent;
    public boolean visited;
    public boolean path;
    public boolean wall;
    public int cost;
    public boolean jmp;
    public boolean terminal;
    
    
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
