
package polunhakija.UI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import polunhakija.Node;

public class GridCanvas extends Canvas {
    
    private Node[][] map;
    private int size = 2;
    
    public int getSizeMul() {
        return(size);
    }
    
    public void changeSize(int x, int y) {
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
                    g.setColor(Color.LIGHT_GRAY);
                }
                if (node.path) {
                    g.setColor(Color.green);
                } 
                if (node.jmp) {
                    g.setColor(Color.red);
                }
                if (node.terminal) {
                    g.setColor(Color.blue);
                }
                
                g.fillRect(node.x * size, node.y * size, size, size);
                
            }
        }
    }
    
}
