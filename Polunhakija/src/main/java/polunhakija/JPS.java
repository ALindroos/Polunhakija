
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
        
        
        if (node.wall || node.visited) {
            return null;
        }
    
        
        if (node.x == goal.x && node.y == goal.y) {
            return node;
        }
        
        node.visited = true;    
        
        
        //wall or visited node straight ahead, so this row can be ignored
        if (map[node.x + xDir][node.y].wall ||
                map[node.x + xDir][node.y].visited) {
            return null;
        }
        
        // goal straight ahead, node is jump point
        if (node.x + xDir == goal.x && node.y == goal.y) {
            return node;
        }
        
        //wall directly next to the node, but diagonally forward node is open next
        //to it, so the node has forced neighbour and needs to be examined later
        if ( (map[node.x][node.y + 1].wall && !map[node.x + xDir][node.y + 1].wall) ||
                (map[node.x][node.y - 1].wall && !map[node.x + xDir][node.y - 1].wall) ) {
            return node;
        }
        
        //recursively iterate on the row
        return scanPathHor(map[node.x + xDir][node.y], xDir);
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
        
        if (node.x == goal.x && node.y == goal.y) {
            return node;
        }
        node.visited = true;

        if (map[node.x][node.y + yDir].wall || 
                map[node.x][node.y + yDir].visited) {
            return null;
        }
        
        if (node.x == goal.x && node.y + yDir == goal.y) {
            return node;
        }
        
        if ( (map[node.x + 1][node.y].wall && !map[node.x + 1][node.y + yDir].wall) ||
                (map[node.x - 1][node.y].wall && !map[node.x - 1][node.y + yDir].wall) ) {
            return node;
        }
        return scanPathVer(map[node.x][node.y + yDir], yDir);
    }
    
    
    /**
     * Examine current node in the openSet, first scan all ortogonal lines,
     * then expand diagonally if no jump points are found
     * @param node
     * @return 
     */
    private Node examineNode(Node node) {
        
        //early exit
        if (node.x == goal.x && node.y == goal.y) {
            return node;
        }
        
        Node tempNode;
        node.visited = true;
        
        //scan all ortogonal lines
        Node xPos = scanPathHor(map[node.x + 1][node.y], 1);
        Node xNeg = scanPathHor(map[node.x -1][node.y], -1);
        Node yPos = scanPathVer(map[node.x][node.y + 1], 1);
        Node yNeg = scanPathVer(map[node.x][node.y - 1], -1);
        
        
        //expand diagonally when possible
        //diag x,y
        if (!map[node.x + 1][node.y + 1].wall &&
                !map[node.x + 1][node.y + 1].visited) {
            tempNode = map[node.x + 1][node.y + 1];
            tempNode.parent = node;
            examineNode(tempNode);
        }
        
        //diag x, -y
        if (!map[node.x + 1][node.y - 1].wall &&
                !map[node.x + 1][node.y - 1].visited) {
            tempNode = map[node.x + 1][node.y - 1];
            tempNode.parent = node;
            examineNode(tempNode);
        }
        
        //diag -x,y
        if (!map[node.x - 1][node.y + 1].wall &&
                !map[node.x - 1][node.y + 1].visited) {
            tempNode = map[node.x - 1][node.y + 1];
            tempNode.parent = node;
            examineNode(tempNode);
        }
        
        //diag -x,-y
        if (!map[node.x - 1][node.y - 1].wall &&
                !map[node.x - 1][node.y - 1].visited) {
            tempNode = map[node.x - 1][node.y - 1];
            tempNode.parent = node;
            examineNode(tempNode);
        }
        

        //if any of the scans return node, a jump point is found and it will be
        //added to the openNodes to examine later
        
        if (xPos != null) {
            node.jmp = true;
            xPos.jmp = true;
            xPos.parent = node;
            openNodes.insert(xPos);
            
        }
        if (xNeg != null) {
            node.jmp = true;
            xNeg.jmp = true;
            xNeg.parent = node;
            openNodes.insert(xNeg);
        }
        if (yPos != null) {
            node.jmp = true;
            yPos.jmp = true;
            yPos.parent = node;
            openNodes.insert(yPos);
        }
        if (yNeg != null) {
            node.jmp = true;
            yNeg.jmp = true;
            yNeg.parent = node;
            openNodes.insert(yNeg);
        }

        
        return null;
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
        this.goal = goal;
        openNodes.insert(start);
        
        while(!openNodes.isEmpty()) {
            Node current = openNodes.remove();
            Node tempNode = examineNode(current);
        }
        
        //calculate distance between all start and goal through the jump points used
        Node node = map[goal.x][goal.y];
        while (node.parent != null) {
            pathL = pathL + diagDis(node, node.parent);
            map[node.x][node.y].path = true;
            node = node.parent;          
        }  
        
  
        return pathL;
    }   
}
