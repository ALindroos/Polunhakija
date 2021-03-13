
package polunhakija;

import java.time.Duration;
import java.time.Instant;

/**
 * implementation of Dijkstra's algorithm with 8-way moves
 * 
 */
public class Dijkstra {
    
    private double pathL;
    private Node[][] map;
    private double runTime;
    private int examNodes;
     

    public double getRunTime() {
        return runTime;
    }
    
    /**
     * return the amount of nodes examined (added to heap) during search
     * @return 
     */
    public int getExamNodes() {
        return examNodes;
    }
    
    /**
     * Checks if the algorithm is trying to cross corners
     * @param node current node
     * @param xDir movement in x-coords
     * @param yDir movement in y-coords
     * @return true if path is crossing a corner, false otherwise
     */
    private boolean checkCorners(Node current, int xDir, int yDir) {
        
        if(map[current.x + xDir][current.y].wall || 
                map[current.x][current.y + yDir].wall) {
            return true;
        }
        return false;
    }
    
    /**
     * examine neighbour of current node in given direction and return it if it needs
     * to be examined later
     * @param current currently examined node, parent of its neighbours
     * @param xDir offset in x-coordinates, i.e +1 is right
     * @param yDir offset in y-coordinates
     * @return Node if it follows requirements, else null
     */
    private Node checkNeighbour(Node current, int xDir, int yDir) {
        Node next = map[current.x + xDir][current.y + yDir];
        
        //adjust distance to next node based on if its diagonal or not
        double stepDistance = 1;
        if (xDir != 0 && yDir != 0) {
            stepDistance = Math.sqrt(2);
        }
        
        if (!next.wall && next.distance > current.distance + stepDistance &&
                !checkCorners(current, xDir, yDir)) {
            
            next.distance = current.distance + stepDistance;
            next.priority = next.distance;            
            next.parent = current;
            return next;
        }
        
        return null;
    }
    
    /**
     * updates the shortest path to the map, so it can be drawn
     * @param goal
     * @param start 
     */
    private void updatePath(Node goal, Node start) {
        Node node = map[goal.x][goal.y];
        while (node.parent != null) {
            map[node.x][node.y].path = true;
            node = node.parent;
        }  
        map[goal.x][goal.y].terminal = true;
        map[start.x][start.y].terminal = true;
    }
    
    /**
     * Find path from start to goal in a given map
     * @param map representation of the map in 2D array of Nodes
     * @param start start Node (position)
     * @param goal goal Node (position)
     * @return length of the shortest path found
     */
    public double findPath(Node[][] map, Node start, Node goal) {
        
        Instant a = Instant.now();
        
        this.map = map;
        int width_x = map.length;
        int height_y = map[0].length;
        BinaryHeap openNodes = new BinaryHeap(width_x * height_y);
        examNodes = 0;
       
        
        start.distance = 0;
        openNodes.insert(start);
        
        while (!openNodes.isEmpty()) {
            Node current = openNodes.remove();            
            pathL = current.distance;
            current.visited = true;
            examNodes++;
            
            //early exit
            if (current.x == goal.x && current.y == goal.y) {
                break;
            }
            
            //check neighbours 
            openNodes.insert(checkNeighbour(current,  0, -1)); //up
            openNodes.insert(checkNeighbour(current,  0,  1)); //down
            openNodes.insert(checkNeighbour(current,  1,  0)); //right  
            openNodes.insert(checkNeighbour(current, -1,  0)); //left
            openNodes.insert(checkNeighbour(current,  1, -1)); //top-right
            openNodes.insert(checkNeighbour(current, -1, -1)); //top-left
            openNodes.insert(checkNeighbour(current,  1,  1)); //bottom-right
            openNodes.insert(checkNeighbour(current, -1,  1)); //bottom-left
            
        }
        
        Instant b = Instant.now();
        runTime = Duration.between(a, b).getNano() / 1000000;
        
        updatePath(goal, start);
        
        return pathL;
        
    } 
}
