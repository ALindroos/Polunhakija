
package polunhakija;


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
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.distance = Integer.MAX_VALUE;
    }
}
