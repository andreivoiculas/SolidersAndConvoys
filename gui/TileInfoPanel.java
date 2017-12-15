package gui;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
public class TileInfoPanel extends JPanel
{
  private JLabel resourceType = new JLabel("No tile selected yet"),
                 tileType  = new JLabel("No tile selected yet"),
                 resourceValue = new JLabel("No tile selected yet"),
                 resourceIcon = new JLabel("");
  /**
   * the default vesion of {@link #TileInfoPanel(Tile) TileInfoPanel}
   */
  public TileInfoPanel()
  {
    JPanel namePanel = new JPanel(),
           resNamePanel = new JPanel(),
           resValuePanel = new JPanel();
    namePanel.add(new JLabel("Tile Type: "));
    namePanel.add(tileType);
    namePanel.setLayout(new GridLayout(1,0));
    resNamePanel.add(new JLabel("Resource Type: "));
    resNamePanel.add(resourceType);
    resNamePanel.add(resourceIcon);
    resNamePanel.setLayout(new GridLayout(1,0));
    resValuePanel.add(new JLabel("Resource Amount: "));
    resValuePanel.add(resourceValue);
    resValuePanel.setLayout(new GridLayout(1,0));
    add(namePanel);
    add(resNamePanel);
    add(resValuePanel);
    setLayout(new GridLayout(3,0));
  }
  public void changeDisplayedTile(String tileName,String resName,
                                  ImageIcon resIcon,int resValue)
  {
    tileType.setText(tileName);
    resourceType.setText(resName);
    JLabel icon = new JLabel("");
    resourceIcon.setIcon(resIcon);
    resourceValue.setText("" + resValue);
  }  
}