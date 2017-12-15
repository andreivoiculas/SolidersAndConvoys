package gui;
import game.Map;
import game.Tile;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.OverlayLayout;
import java.awt.FlowLayout;


public  class MapGUI 
{
  private final JPanel[][] mapGUI;

  public MapGUI(Map tileMap)
  {
    mapGUI = new JPanel[tileMap.getHeight()][tileMap.getLenght()];
    Tile[][] map = tileMap.getMapTiles();
    for (int row = 0;row < tileMap.getHeight() ;row++ ) 
      for (int column = 0;column < tileMap.getLenght() ;column++ ) 
      {
        mapGUI[row][column] = new JPanel();
        mapGUI[row][column].setLayout(new OverlayLayout(mapGUI[row][column]));
        mapGUI[row][column].add(map[row][column].getIcon());       
      }
  }
  public JPanel[][] getMapPanels()
  {
    return mapGUI;
  }
}