
package polunhakija;


public class JPS {
    
    private double pathL;
    private Node[][] map;
    private BinaryHeap openNodes;
    private Node goal;
    
    private void printState(Node[][] map) {
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
    

    private int manhattanDis(Node a, Node b) {
        return (Math.abs(a.x - b.x) + Math.abs(a.y - b.y));
    }
    
    
    
    private Node scanPathHor(Node node, int xDir) {
 
        if (node.wall) {
            return null;
        }
    
        
        if (node.x == goal.x && node.y == goal.y) {
            return node;
        }
        
        node.visited = true;    
        
        if (map[node.x + xDir][node.y].wall ||
                map[node.x + xDir][node.y].visited) {
            return null;
        }
        
        if (node.x + xDir == goal.x && node.y == goal.y) {
            return node;
        }
        
        if ( (map[node.x][node.y + 1].wall && !map[node.x - xDir][node.y + 1].wall) ||
                (map[node.x][node.y - 1].wall && !map[node.x - xDir][node.y - 1].wall) ) {
            return node;
        }
        
        return scanPathHor(map[node.x + xDir][node.y], xDir);
    }
    
    private Node scanPathVer(Node node, int yDir) {
        
        
        if (node.wall) {
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
        
        if ( (map[node.x + 1][node.y].wall && !map[node.x + 1][node.y - yDir].wall) ||
                (map[node.x - 1][node.y].wall && !map[node.x - 1][node.y- yDir].wall) ) {
            return node;
        }
        return scanPathVer(map[node.x][node.y + yDir], yDir);
    }
    
    private Node examineNode(Node node) {
        
        Node tempNode;
        
        if (node.x == goal.x && node.y == goal.y) {
            return node;
        }
        
        node.visited = true;
        
        Node xPos = scanPathHor(map[node.x + 1][node.y], 1);
        Node xNeg = scanPathHor(map[node.x -1][node.y], -1);
        Node yPos = scanPathVer(map[node.x][node.y + 1], 1);
        Node yNeg = scanPathVer(map[node.x][node.y - 1], -1);
        
        
        if (!map[node.x + 1][node.y + 1].wall &&
                !map[node.x + 1][node.y + 1].visited) {
            examineNode(map[node.x + 1][node.y + 1]);
        }
        
        if (!map[node.x + 1][node.y - 1].wall &&
                !map[node.x + 1][node.y - 1].visited) {
            examineNode(map[node.x + 1][node.y - 1]);
        }
        
        if (!map[node.x - 1][node.y + 1].wall &&
                !map[node.x - 1][node.y + 1].visited) {
            examineNode(map[node.x - 1][node.y + 1]);
        }
        
        if (!map[node.x - 1][node.y - 1].wall &&
                !map[node.x - 1][node.y - 1].visited) {
            examineNode(map[node.x - 1][node.y - 1]);
        }
        

        if (xPos != null) {
            node.jmp = true;
            xPos.jmp = true;
            openNodes.insert(xPos);
            
        }
        if (xNeg != null) {
            node.jmp = true;
            xNeg.jmp = true;
            openNodes.insert(xNeg);
        }
        if (yPos != null) {
            node.jmp = true;
            yPos.jmp = true;
            openNodes.insert(yPos);
        }
        if (yNeg != null) {
            node.jmp = true;
            yNeg.jmp = true;
            openNodes.insert(yNeg);
        }

        
        return null;
    }
    
    
    public Node[][] findPath(Node[][] map, Node start, Node goal) {
        
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
            
            System.out.println(current.x + ":" + current.y);
            
            if (current.x == goal.x && current.y == goal.y) {
                System.out.println("GOAL");
            }
        }
        
        
        

        //testPrint
        printState(map);
        
  
        return map;
    }   
}
