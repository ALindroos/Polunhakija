
package polunhakija;


public class JPS {
    
    private int pathL;
    private Node[][] map;
    private BinaryHeap openNodes;
    private int limit = 0;
    
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
        node.visited = true;
        if (map[node.x + xDir][node.y].wall ||
                map[node.x + xDir][node.y].visited) {
            return null;
        }
        
        if ( (map[node.x][node.y + 1].wall && !map[node.x - xDir][node.y + 1].wall) ||
                (map[node.x][node.y - 1].wall && !map[node.x - xDir][node.y - 1].wall) ) {
            node.jmp = true;
            return node;
        }
        
        return scanPathHor(map[node.x + xDir][node.y], xDir);
    }
    
    private Node scanPathVer(Node node, int yDir) {
        node.visited = true;
        if (map[node.x][node.y + yDir].wall || 
                map[node.x][node.y + yDir].visited) {
            return null;
        }
       
        if ( (map[node.x + 1][node.y].wall && !map[node.x + 1][node.y - yDir].wall) ||
                (map[node.x - 1][node.y].wall && !map[node.x - 1][node.y- yDir].wall) ) {
            node.jmp = true;
            return node;
        }
        return scanPathVer(map[node.x][node.y + yDir], yDir);
    }
    
    private void examineNode(Node node) {
        Node xPos = scanPathHor(node, 1);
        Node xNeg = scanPathHor(node, -1);
        Node yPos = scanPathVer(node, 1);
        Node yNeg = scanPathVer(node, -1);
        
        
        if (xPos == null && yPos == null &&
                !map[node.x + 1][node.y + 1].wall &&
                !map[node.x + 1][node.y + 1].visited) {
            examineNode(map[node.x + 1][node.y + 1]);
        }
        
        if (xPos == null && yNeg == null &&
                !map[node.x + 1][node.y - 1].wall &&
                !map[node.x + 1][node.y - 1].visited) {
            examineNode(map[node.x + 1][node.y - 1]);
        }
        
        if (xNeg == null && yPos == null &&
                !map[node.x - 1][node.y + 1].wall &&
                !map[node.x - 1][node.y + 1].visited) {
            examineNode(map[node.x - 1][node.y + 1]);
        }
        
        if (xNeg == null && yNeg == null &&
                !map[node.x - 1][node.y - 1].wall &&
                !map[node.x - 1][node.y - 1].visited) {
            examineNode(map[node.x - 1][node.y - 1]);
        }
        
        int c = 10;
        
        if (xPos != null && limit < c) {
            openNodes.insert(xPos);
            limit++;
        }
        
        if (xNeg != null && limit < c) {
            openNodes.insert(xNeg);
            limit++;
        }
        
        if (yPos != null && limit < c) {
            openNodes.insert(yPos);
            limit++;
        }
        
        if (yNeg != null && limit < c) {
            openNodes.insert(yNeg);
            limit++;
        }
        
        
        

    }
    
    
    public Node[][] findPath(Node[][] map, Node start, Node goal) {
        
        this.map = map;
        int width_x = map.length;
        int height_y = map[0].length;
        openNodes = new BinaryHeap(width_x * height_y);
        
        map[start.x][start.y].path = true;
        map[goal.x][goal.y].path = true;
        
        start.distance = 0;
        openNodes.insert(start);
        
        while(!openNodes.isEmpty()) {
            Node current = openNodes.remove();
            
            System.out.println(current.x + ":" + current.y);
            
            examineNode(current);
            
            
        }
        
        pathL = 0;
        Node node = map[goal.x][goal.y];
        while (node.parent != null) {
            map[node.x][node.y].path = true;
            node = node.parent;
            pathL++;
        }  

        //testPrint
        printState(map);
        
  
        return map;
    }   
}
