
package polunhakija;


public class Dijkstra {
    
    private double pathL;
    private Node[][] map;
    
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
        
    public double getPathL() {
        return pathL;
    }
    
    private Node checkNeighbour(Node current, int xDir, int yDir) {
        Node node = map[current.x + xDir][current.y + yDir];
        
        if (!node.visited && !node.wall &&
                node.distance > current.distance + node.cost) {
            
            //check if diagonal and adjust distance
            if (Math.abs(xDir) + Math.abs(yDir) == 1) {
                node.distance = current.distance + node.cost;
            } else {
                node.distance = current.distance + Math.sqrt(2);
            }
            
            node.parent = current;
            return node;
        }
        
        return null;
    }
    
    
    public double findPath(Node[][] map, Node start, Node goal) {
        
        this.map = map;
        int width_x = map.length;
        int height_y = map[0].length;
        int exDistance = 1;
        BinaryHeap openNodes = new BinaryHeap(width_x * height_y);
       
        
        start.distance = 0;
        openNodes.insert(start);
        
        while (!openNodes.isEmpty()) {
            Node current = openNodes.remove();            
            Node tempNode;
            
            //early exit
            if (current.x == goal.x && current.y == goal.y) {
                break;
            }
            
            //check neighbours
            //ortogonal
            //up         
            openNodes.insert(checkNeighbour(current, 0, -1));
            //down
            openNodes.insert(checkNeighbour(current, 0, 1));
            //right
            openNodes.insert(checkNeighbour(current, 1, 0));
            //left
            openNodes.insert(checkNeighbour(current, -1, 0));
            
            
            //diagonal
            //up-right
            openNodes.insert(checkNeighbour(current, 1, -1));
            //up-left
            openNodes.insert(checkNeighbour(current, -1, -1));
            //down-right
            openNodes.insert(checkNeighbour(current, 1, 1));
            //down-left
            openNodes.insert(checkNeighbour(current, -1, 1));
            

            current.visited = true;  
            pathL = current.distance;
        }
        
        Node node = map[goal.x][goal.y];
        while (node.parent != null) {
            map[node.x][node.y].path = true;
            node = node.parent;
        }  
        
        return pathL;
        
    }
    
    
}
