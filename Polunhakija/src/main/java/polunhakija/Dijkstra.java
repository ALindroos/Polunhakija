
package polunhakija;

/**
 * implementation of Dijkstra's algorithm with 8-way moves
 * 
 */
public class Dijkstra {
    
    private double pathL;
    private Node[][] map;
    private long runTime;
    
    
    public double getPathL() {
        return pathL;
    }
    
    public long getRunTime() {
        return runTime;
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
        
        if (!next.visited && !next.wall &&
                next.distance > current.distance + next.cost &&
                !checkCorners(current, xDir, yDir)) {
            
            //check if diagonal and adjust distance
            if (Math.abs(xDir) + Math.abs(yDir) == 1) {
                next.distance = current.distance + next.cost;
            } else {
                next.distance = current.distance + Math.sqrt(2);
            }
            next.priority = next.distance;
            
            next.parent = current;
            return next;
        }
        
        return null;
    }
    
    private void updatePath(Node goal) {
        Node node = map[goal.x][goal.y];
        while (node.parent != null) {
            map[node.x][node.y].path = true;
            node = node.parent;
        }  
    }
    
    /**
     * Find path from start to goal in a given map
     * @param map representation of the map in 2D array of Nodes
     * @param start start Node (position)
     * @param goal goal Node (position)
     * @return length of the shortest path found
     */
    public double findPath(Node[][] map, Node start, Node goal) {
        
        this.map = map;
        int width_x = map.length;
        int height_y = map[0].length;
        BinaryHeap openNodes = new BinaryHeap(width_x * height_y);
       
        
        start.distance = 0;
        openNodes.insert(start);
        
        long aTime = System.currentTimeMillis();
        while (!openNodes.isEmpty()) {
            Node current = openNodes.remove();            
            pathL = current.distance;
            current.visited = true;  
            
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
        long bTime = System.currentTimeMillis();
        runTime = bTime - aTime;
        
        updatePath(goal);
        return pathL;
        
    } 
}
