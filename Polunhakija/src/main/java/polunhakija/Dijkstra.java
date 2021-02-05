
package polunhakija;


public class Dijkstra {
    
    private int pathL;
    
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
    
    private void paint() {
        
    }
    
    public int getPathL() {
        return pathL;
    }
    
    
    public Node[][] findPath(Node[][] map, Node start, Node goal) {
        
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
            //up           
            if (current.y > 0) {
                tempNode = map[current.x][current.y -1];
                if (!tempNode.visited && !tempNode.wall && 
                        tempNode.distance > current.distance + exDistance) {
                    tempNode.distance = current.distance + exDistance;
                    tempNode.parent = current;
                    openNodes.insert(tempNode);  
                }
            }
 
            //down
            if (current.y < height_y) {
                tempNode = map[current.x][current.y +1];
                if (!tempNode.visited && !tempNode.wall && 
                        tempNode.distance > current.distance + exDistance) {
                    tempNode.distance = current.distance + exDistance;
                    tempNode.parent = current;
                    openNodes.insert(tempNode);  
                }
            }
            
            //right
            if (current.y < width_x) {
                tempNode = map[current.x + 1][current.y];
                if (!tempNode.visited && !tempNode.wall &&
                        tempNode.distance > current.distance + exDistance) {
                    tempNode.distance = current.distance + exDistance;
                    tempNode.parent = current;
                    openNodes.insert(tempNode);  
                }
            }
            
            //left
            if (current.y > 0) {
                tempNode = map[current.x - 1][current.y];
                if (!tempNode.visited && !tempNode.wall &&
                        tempNode.distance > current.distance + exDistance) {
                    tempNode.distance = current.distance + exDistance;
                    tempNode.parent = current;
                    openNodes.insert(tempNode);  
                }
            }

            current.visited = true;   

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
