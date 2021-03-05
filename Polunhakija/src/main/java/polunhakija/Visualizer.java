
package polunhakija;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

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
    
    public Node[][] getMap() {
        if (swap) {
            return mapD;
        }
        return mapJ;
    }
    
    public void setUp(String mapName) {
        MapLoader mapLoader = new MapLoader();
        
        mapD = mapLoader.loadMap(mapName);
        mapJ = mapLoader.loadMap(mapName);
        Dijkstra dijkstra = new Dijkstra();
        JPS jps = new JPS();
        
        
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
        
        JTextField startX = new JTextField();
        JTextField startY = new JTextField();
        JTextField goalX = new JTextField();
        JTextField goalY = new JTextField();  
        
        
        startX.setBounds(mapD.length * 2,      100, 50, 30);
        startY.setBounds(mapD.length * 2 + 50, 100, 50, 30);
        goalX.setBounds(mapD.length * 2,      130, 50, 30);
        goalY.setBounds(mapD.length * 2 + 50, 130, 50, 30);
        
        
        JButton changePath = new JButton("find path");
        changePath.setBounds(mapD.length * 2, 160, 100, 50);
        changePath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                mapD = mapLoader.loadMap(mapName);
                mapJ = mapLoader.loadMap(mapName);
                
                Node start = new Node(Integer.parseInt(startX.getText()), 
                        Integer.parseInt(startY.getText()));
                Node goal = new Node(Integer.parseInt(goalX.getText()), 
                        Integer.parseInt(goalY.getText()));
                
                dijkstra.findPath(mapD, start, goal);
                jps.findPath(mapJ, start, goal);
                
                update(getMap());
                
            }
        });
        
        JButton clearCoords = new JButton("Clear");
        clearCoords.setBounds(mapD.length * 2, 210, 100, 50);
        clearCoords.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startX.setText("");
                startY.setText("");
                goalX.setText("");
                goalY.setText("");
            }
        });
        
        
        canvas = new GridCanvas();
        canvas.changeSize(mapD.length + 50, mapD[0].length);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(changeAlgo);
        frame.add(changePath);
        frame.add(clearCoords);
        frame.add(startX);
        frame.add(startY);
        frame.add(goalX);
        frame.add(goalY);
        frame.add(canvas);
        frame.pack();
    }
    
}
