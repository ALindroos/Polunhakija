
package polunhakija;

/**
 * implementation of Jump point search (JPS) algorithm
 *
 */
public class JPS {
    
    private double pathL;
    private Node[][] map;
    private BinaryHeap openNodes;
    private Node goal;
    private boolean earlyExit;
    
    public void printState() {
        for (int y=0; y<map[0].length; y++) {
            for (int x=0; x<map.length; x++) {
                
                if (map[x][y].wall) {
                    System.out.print("&");
                    continue;
                    
                }
                if (map[x][y].path) {
                    System.out.print("P");
                    continue;
                }
                
                if (map[x][y].jmp) {
                    System.out.print("J");
                    continue;
                } 
                
                if (map[x][y].visited) {
                    System.out.print("v");
                    continue;
                }
                System.out.print(".");            
            }
            System.out.println();
        }
        System.out.println("Path: " + pathL );       
    }
    

    /**
     * Calculate diagonal distance between 2 nodes
     * @param a first node
     * @param b 2nd node
     * @return 
     */
    private double diagDis(Node a, Node b) {
        double dx = Math.abs(a.x - b.x);
        double dy = Math.abs(a.y - b.y);
        return Math.sqrt((dx * dx) + (dy * dy));
    }
    
    
    /**
     * Scan a row horizontally for empty nodes until it hits either a wall or jump point
     * @param node current node
     * @param xDir direction of the movement, i.e. +1 = right
     * @return Node if it is jump point or goal
     */
    private Node scanPathHor(Node node, int xDir) {
        
        Node next = map[node.x + xDir][node.y];
        
        if (node.wall || node.visited) {
            return null;
        }
    
        node.visited = true; 
        node.distance = map[node.x - xDir][node.y].distance + node.cost;
        
        if (node.x == goal.x && node.y == goal.y) {
            return node;
        }
        
        //wall or visited node straight ahead, so this row can be ignored
        if (next.wall || next.visited) {
            return null;
        }
        
        // goal straight ahead, node is jump point
        if (next.x == goal.x && next.y == goal.y) {
            return node;
        }
        
        //wall directly next to the node, but diagonally forward node is open next
        //to it, so the node has forced neighbour and needs to be examined later
        if ( (map[node.x][node.y + 1].wall && !map[next.x][next.y + 1].wall) ||
                (map[node.x][node.y - 1].wall && !map[next.x][next.y - 1].wall) ) {
            return node;
        }
        
        //recursively iterate on the row
        return scanPathHor(next, xDir);
    }
    
    /**
     * Same as horizontal scan
     * @param node current node
     * @param yDir y offset
     * @return 
     */
    private Node scanPathVer(Node node, int yDir) {
             
        if (node.wall || node.visited) {
            return null;
        }
        
        Node next = map[node.x][node.y + yDir];
        node.visited = true;
        node.distance = map[node.x][node.y - yDir].distance + node.cost;
        
        if (node.x == goal.x && node.y == goal.y) {
            return node;
        }

        if (next.wall || next.visited) {
            return null;
        }
        
        if (next.x == goal.x && next.y == goal.y) {
            return node;
        }
        
        if ( (map[node.x + 1][node.y].wall && !map[next.x + 1][next.y].wall) ||
                (map[node.x - 1][node.y].wall && !map[next.x - 1][next.y].wall) ) {
            return node;
        }
        return scanPathVer(next, yDir);
    }
    
    /**
     * if any horizontal or vertical scan finds an jumpNode,
     * it is added to openNodes to be examined later
     * @param found found jump point in a scan
     * @param current currently examined node
     */
    private void addJumpPoint(Node found, Node current) {
        if (found == null) {
            return;
        }
        current.jmp = true;
        found.jmp = true;
        found.parent = current;
        found.distance = found.distance + diagDis(found, goal);
        openNodes.insert(found);
    }
    
    
    /**
     * check if next diagonal node can be moved to and examine it
     * @param node currently examined node
     * @param xDir direction in x-coordinates
     * @param yDir direction in y-coordinates
     */
    public void moveDiag(Node node, int xDir, int yDir) {
        Node target = map[node.x + xDir][node.y + yDir];
        
        if (!target.wall && !target.visited) {
            target.parent = node;
            target.distance = node.distance + Math.sqrt(2);
            examineNode(target, xDir, yDir);
        }
    }
    
    
    /**
     * Examine current node in the openSet, first scan all ortogonal lines,
     * then expand diagonally if no jump points are found
     * @param node
     * @return 
     */
    private void examineNode(Node node, int xDir, int yDir) {
        node.visited = true;
        
        //early exit
        if (earlyExit) {
            return;
        }
        if (node.x == goal.x && node.y == goal.y) {
            earlyExit = true;
            return;
        }

        //scan all ortogonal lines
        //if any of the scans return node, a jump point is found and it will be
        //added to the openNodes to examine later
        addJumpPoint(scanPathHor(map[node.x + 1][node.y], 1), node);
        addJumpPoint(scanPathHor(map[node.x -1][node.y], -1), node);
        addJumpPoint(scanPathVer(map[node.x][node.y + 1], 1), node);
        addJumpPoint(scanPathVer(map[node.x][node.y - 1], -1), node);
         
        //expand diagonally when possible, keeping the same direction
        //in case of a new jump point expand in all directions
        if (xDir == 0 && yDir == 0) {
            moveDiag(node, 1, 1);
            moveDiag(node, 1, -1);
            moveDiag(node, -1, 1);
            moveDiag(node, -1, -1);
        } else {
            moveDiag(node, xDir, yDir);
        }
    }
    
    private double calculatePath() {
        pathL = 0;
        Node node = map[goal.x][goal.y];
        while (node.parent != null) {
            pathL = pathL + diagDis(node, node.parent);
            map[node.x][node.y].path = true;
            node = node.parent;          
        }
        return pathL;
    }
    
    /**
     * Finds shortest path on the given graph between start and goal using
     * jump point search
     * @param map
     * @param start
     * @param goal
     * @return 
     */
    public double findPath(Node[][] map, Node start, Node goal) {
        
        this.map = map;
        int width_x = map.length;
        int height_y = map[0].length;
        openNodes = new BinaryHeap(width_x * height_y);
        
        
        map[start.x][start.y].path = true;
        map[goal.x][goal.y].path = true;
        
        start.distance = 0;
        map[start.x][start.y].distance = 0;
        this.goal = goal;
        openNodes.insert(start);
        earlyExit = false;
        
        while(!openNodes.isEmpty()) {
            Node current = openNodes.remove();
            examineNode(current, 0, 0);
            
        }
  
        return calculatePath();
    }   
}
