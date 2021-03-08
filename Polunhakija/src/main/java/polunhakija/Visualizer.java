
package polunhakija;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private JLabel pathLength;
    private double pathJ;
    private double pathD;
    
    
    /**
     * updates the screen and shows the specified map
     * also updates path display
     * @param map 
     */
    public void update(Node[][] map) {
        if (swap) {
            pathLength.setText("path: " + pathD);
        } else {
            pathLength.setText("path: " + pathJ);
        }
        canvas.updateState(map);
        canvas.repaint();
    }
    
    /**
     * returns which map is now supposed to be displayed, decided by the swap parameter
     * true = Dijkstra, false = JPS
     * @return 
     */
    public Node[][] getMap() {
        if (swap) {
            return mapD;
        }
        return mapJ;
    }
    
    /**
     * sets up the screen and its components
     * @param mapName 
     */
    public void setUp(String mapName) {
        MapLoader mapLoader = new MapLoader();
        
        mapD = mapLoader.loadMap(mapName);
        mapJ = mapLoader.loadMap(mapName);
        Dijkstra dijkstra = new Dijkstra();
        JPS jps = new JPS();
        
        
        swap = true;
        
        JButton changeAlgo = new JButton("change");
        changeAlgo.setBounds(mapD.length * 2, 0, 100, 60);
        changeAlgo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                swap = !swap;
                
                if (swap) {
                    changeAlgo.setText("Dijkstra");
                } else {
                    changeAlgo.setText("JPS");
                }
                update(getMap());
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
                
                pathD = dijkstra.findPath(mapD, start, goal);
                pathJ = jps.findPath(mapJ, start, goal);
                
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
        
        pathLength = new JLabel();
        pathLength.setBounds(mapD.length * 2 - 140, 10, 120, 20);
        
        
        
        
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
        frame.add(pathLength);
        frame.add(canvas);
        frame.pack();
    }
    
}
