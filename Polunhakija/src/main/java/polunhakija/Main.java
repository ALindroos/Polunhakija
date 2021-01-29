package polunhakija;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


public class Main {
    
    public static void main(String[] args) {
        MapLoader mapLoader = new MapLoader();
        Node[][] map = mapLoader.loadMap();
        
        int width_x = map.length;
        int height_y = map[0].length;
        int exDistance = 1;
        
        
        Comparator<Node> adjentComp = (left, right) -> {
            if (left.distance > right.distance) {
                return 1;
            } else {
                return -1;
            }
        };
        
        Queue<Node> openNodes = new PriorityQueue(width_x * height_y, adjentComp);
        Node start = new Node(29, 10);
        Node goal = new Node(47, 84);
        
        start.distance = 0;
        
        openNodes.add(start);
        
        while (!openNodes.isEmpty()) {
            Node current = openNodes.remove();
            //System.out.println("x: " + current.x);
            //System.out.println("y: " + current.y);
            //System.out.println("------");
            
            if (current.x == goal.x && current.y == goal.y) {
                System.out.println("GOAALLL");
                break;
            }
            
            
            Node tempNode;
            
            //check neighbours
            //up
            
            if (current.y > 0) {
                tempNode = map[current.x][current.y -1];
                if (!tempNode.visited && !tempNode.wall && 
                        tempNode.distance > current.distance + exDistance) {
                    tempNode.distance = current.distance + exDistance;
                    tempNode.parent = current;
                    openNodes.add(tempNode);  
                }
            }
            
            
            //down
            if (current.y < height_y) {
                tempNode = map[current.x][current.y +1];
                if (!tempNode.visited && !tempNode.wall && 
                        tempNode.distance > current.distance + exDistance) {
                    tempNode.distance = current.distance + exDistance;
                    tempNode.parent = current;
                    openNodes.add(tempNode);  
                }
            }
            
            //right
            if (current.y < width_x) {
                tempNode = map[current.x + 1][current.y];
                if (!tempNode.visited && !tempNode.wall &&
                        tempNode.distance > current.distance + exDistance) {
                    tempNode.distance = current.distance + exDistance;
                    tempNode.parent = current;
                    openNodes.add(tempNode);  
                }
            }
            
            //left
            if (current.y > 0) {
                tempNode = map[current.x - 1][current.y];
                if (!tempNode.visited && !tempNode.wall &&
                        tempNode.distance > current.distance + exDistance) {
                    tempNode.distance = current.distance + exDistance;
                    tempNode.parent = current;
                    openNodes.add(tempNode);  
                }
            }
            current.visited = true;      
        }
        
        ArrayList<Node> path = new ArrayList<>();
        Node node = map[goal.x][goal.y];
        while (node.parent != null) {
            map[node.x][node.y].cost = 4;
            node = node.parent;
        }

        
        
        
        
        
        
        
        
        
        for (int y=0; y<map[0].length; y++) {
            for (int x=0; x<map.length; x++) {
                System.out.print(map[x][y].cost);            
            }
            System.out.println();
        }
        
        
        
    }   
}
