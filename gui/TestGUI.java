package gui;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import game.Tile;
import java.awt.event.MouseListener;
import game.Map;
public class TestGUI extends JFrame
{
  private final TileInfoPanel tileInfoPanel = new TileInfoPanel();
  private final MapGUI mapGUI = new MapGUI(new Map(Map.Type.ISLAND,20,20));
  public static TestGUI randomName = new TestGUI();
  public TestGUI()
  {
    setTitle("Test");
    setLocation(100,100);
    JPanel map = new JPanel();
    Container contents = getContentPane();
    JPanel[][] mapPanels = mapGUI.getMapPanels();
    for(int row = 0; row < mapPanels.length;row++)
      for (int column = 0;column < mapPanels[row].length ;column++ ) 
      {
        map.add(mapPanels[row][column]);  
      }
      map.setLayout(new GridLayout(mapPanels.length,0)); 
    setLayout(new BorderLayout());
    contents.add(map,BorderLayout.SOUTH);
    contents.add(tileInfoPanel,BorderLayout.NORTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
  }
  /**
   * It alters a panel inside testGUI depending on what tile the mouse cursor 
   * is over,making it display specific information about said tile. 
   * @param tile is the tile whose info is dislpayed in the panel
   */
  public void setTileInfoPanel(Tile tile)
  {
    tileInfoPanel.changeDisplayedTile(tile.getName(),
                                      tile.getResourceName(),
                                      tile.getResourceIcon(),
                                      tile.getValue());
    pack();
  }


  public static void main(String[] args) 
  {
    randomName.setVisible(true);
  }
}