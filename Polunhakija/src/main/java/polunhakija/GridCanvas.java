
package polunhakija;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class GridCanvas extends Canvas {
    
    private Node[][] map;
    private int size = 8;
    
    public GridCanvas(int x, int y) {
        setSize(x * size, y * size);
    }
    
    public void updateState(Node[][] map) {
        this.map = map;
    }
    
    @Override
    public void paint(Graphics g) {
        draw(g);
    }
    
    public void draw(Graphics g) {
        for (Node[] row : map) {
            for (Node node : row) {
                
                g.setColor(Color.white);
                if (node.wall) {
                    g.setColor(Color.black);
                }
                if (node.visited) {
                    g.setColor(Color.PINK);
                }
                if (node.path) {
                    g.setColor(Color.green);
                } 
                
                g.fillRect(node.x * size, node.y * size, size, size);
                
            }
        }
    }
    
}