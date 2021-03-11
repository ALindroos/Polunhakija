
package polunhakija;

import java.time.Duration;
import java.time.Instant;

/**
 * implementation of Jump point search (JPS) algorithm
 *
 */
public class JPS {
    
    private Node[][] map;
    private BinaryHeap openNodes;
    private Node goal;
    private double runTime;
    
    
    public double getRunTime() {
        return runTime;
    }
    
    
    /**
     * Calculate euclidian distance between 2 nodes
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
        
        if (node.x == map.length - 1 || node.x == 0) {
            return null;
        }
        
        Node next = map[node.x + xDir][node.y];
        
        if (node.wall || node.visited) {
            return null;
        }
    
        node.visited = true; 
        node.distance = map[node.x - xDir][node.y].distance + 1;
        
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
        
        //to avoid cutting corners, if a node has wall diagonally behind it,
        //but is open from sides it is considered a jump node
        if ( (!map[node.x][node.y + 1].wall && map[node.x - xDir][node.y+1].wall) ||
                (!map[node.x][node.y-1].wall && map[node.x - xDir][node.y-1].wall) ){
            return node;
        }
        
        /* cut corners
        //wall directly next to the node, but diagonally forward node is open next
        //to it, so the node has forced neighbour and needs to be examined later
        if ( (map[node.x][node.y + 1].wall && !map[next.x][next.y + 1].wall) ||
                (map[node.x][node.y - 1].wall && !map[next.x][next.y - 1].wall) ) {
            return node;
        }*/
        
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
        
        if (node.y == map.length - 1 || node.y == 0) {
            return null;
        }
             
        if (node.wall || node.visited) {
            return null;
        }
        
        Node next = map[node.x][node.y + yDir];
        node.visited = true;
        
        node.distance = map[node.x][node.y - yDir].distance + 1;
        
        if (node.x == goal.x && node.y == goal.y) {
            return node;
        }

        if (next.wall || next.visited) {
            return null;
        }
        
        
        if (next.x == goal.x && next.y == goal.y) {
            return node;
        }
        
        
        //to avoid cutting corners, if a node has wall diagonally behind it,
        //but is open from sides it is considered a jump node
        if ( (!map[node.x + 1][node.y].wall && map[node.x +1][node.y - yDir].wall) ||
                (!map[node.x - 1][node.y].wall && map[node.x - 1][node.y - yDir].wall) ){
            return node;
        }
        
        
        /* cut corners
        if ( (map[node.x + 1][node.y].wall && !map[next.x + 1][next.y].wall) ||
                (map[node.x - 1][node.y].wall && !map[next.x - 1][next.y].wall) ) {
            return node;
        }*/
        
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
        found.priority = found.distance + diagDis(found, goal);
        openNodes.insert(found);
    }
    
    /**
     * checks that moving to a new node in the given direction doesn't cut corners
     * i.e node neighbouring two diagonal nodes is wall 
     * @param current current node
     * @param xDir movement in x-coords
     * @param yDir movement in y-coords
     * @return return true if cuts a corner, otherwise false
     */
    private boolean checkCorners(Node current, int xDir, int yDir) {
        
        if(map[current.x + xDir][current.y].wall || 
                map[current.x][current.y + yDir].wall) {
            return true;
        }
        return false;
    }
    
    
    /**
     * check if next diagonal node can be moved to and examine it
     * @param node currently examined node
     * @param xDir direction in x-coordinates
     * @param yDir direction in y-coordinates
     */
    public void moveDiag(Node node, int xDir, int yDir) {
        Node target = map[node.x + xDir][node.y + yDir];
        
        if (!target.wall && !target.visited && !checkCorners(node, xDir, yDir)) {
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
        
        if (node.x == goal.x && node.y == goal.y) {
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
    
    /**
     * draws all the nodes in the path to map
     * as jump points skip movement in ortogonal lines
     */
    public void drawPath(Node start) {
        Node node = map[goal.x][goal.y];
        Node parent = node.parent;
        while (parent != null) {
            
            if (!node.jmp); {
                node.path = true;
            }
            
            if (diagDis(node, parent) > Math.sqrt(2)) {
                if (node.x - parent.x == 0) {
                    if (node.y - parent.y < 0) {
                        node = map[node.x][node.y + 1];
                    } else {
                        node = map[node.x][node.y - 1];
                    }
                } else {
                    if (node.x - parent.x < 0) {
                        node = map[node.x + 1][node.y];
                    } else {
                        node = map[node.x - 1][node.y];
                    }
                }
            } else {
                node = parent;
                parent = node.parent;
            }  
        }
        map[start.x][start.y].terminal = true;
        map[goal.x][goal.y].terminal = true;
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
        
        Instant a = Instant.now();
        
        this.map = map;
        int width_x = map.length;
        int height_y = map[0].length;
        openNodes = new BinaryHeap(width_x * height_y);
        
        
        start.distance = 0;
        map[start.x][start.y].distance = 0;
        this.goal = goal;
        openNodes.insert(start);           
        
        while(!openNodes.isEmpty()) {
            Node current = openNodes.remove();
            examineNode(current, 0, 0);
        }
        double pathL = map[goal.x][goal.y].distance;
        
        Instant b = Instant.now();
        runTime = Duration.between(a, b).getNano() / 1000000;
        
        drawPath(start);
        
        return pathL;
    }   
}
