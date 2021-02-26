
package polunhakija;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author lindradi
 */
public class Visualizer {
    
    private GridCanvas canvas;
    private Node[][] mapD;
    private Node[][] mapJ;
    private boolean swap;
    private JButton changeAlgo;
    
    public void update(Node[][] map) {
        canvas.updateState(map);
        canvas.repaint();
    }
    
    public void setUp(Node[][] mapD, Node[][] mapJ) {
        this.mapD = mapD;
        this.mapJ = mapJ;
        swap = true;
        
        changeAlgo = new JButton("change");
        changeAlgo.setBounds(mapD.length * 2, 0, 100, 60);
        changeAlgo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                swap = !swap;
                if (swap) {
                    changeAlgo.setText("Dijkstra");
                    update(mapD);
                } else {
                    changeAlgo.setText("JPS");
                    update(mapJ);
                }
            }
        });
        
        
        canvas = new GridCanvas();
        canvas.changeSize(mapD.length + 50, mapD[0].length);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(changeAlgo);
        frame.add(canvas);
        frame.pack();
    }
    
}
