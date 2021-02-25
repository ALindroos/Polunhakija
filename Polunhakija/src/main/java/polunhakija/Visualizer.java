
package polunhakija;

import javax.swing.JFrame;

/**
 *
 * @author lindradi
 */
public class Visualizer {
    
    private GridCanvas canvas;
    
    public void update(Node[][] map) {
        canvas.updateState(map);
        canvas.repaint();
    }
    
    public void setUp(int width, int height) {         
        canvas = new GridCanvas();
        canvas.changeSize(width, height);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(canvas);
        frame.pack();
    }
    
}
