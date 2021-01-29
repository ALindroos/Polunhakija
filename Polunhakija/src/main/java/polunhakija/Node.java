
package polunhakija;


public class Node {
    
    int x;
    int y;
    int distance;
    Node parent;
    boolean visited;
    boolean wall;
    int cost;
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.distance = Integer.MAX_VALUE;
    }
}
