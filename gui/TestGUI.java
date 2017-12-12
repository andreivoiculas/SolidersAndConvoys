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
public class TestGUI extends JFrame
{
  private final TileInfoPanel tileInfoPanel = new TileInfoPanel();
  public static TestGUI randomName = new TestGUI();
  public TestGUI()
  {
    setTitle("Test");
    setLocation(100,100);
    JPanel map = new JPanel();
    map.setLayout(new GridLayout(3,0,0,0));
    Container contents = getContentPane();
    Tile rock,ground,grass;
    for (int i = 1;i<= 10 ;i++ ) 
    {
      rock = new Tile(Tile.Type.ROCK_TILE);
      ground = new Tile(Tile.Type.GROUND_TILE);
      grass = new Tile(Tile.Type.GRASS_TILE);
    map.add(rock.getIcon());
    map.add(ground.getIcon());
    map.add(grass.getIcon());
    }
    setLayout(new BorderLayout());
    contents.add(map,BorderLayout.CENTER);
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
                                      tile.getValue());
    pack();
  }


  public static void main(String[] args) 
  {
    randomName.setVisible(true);
  }
}