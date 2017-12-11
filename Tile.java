package game;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import myUtil.MyMath;
import game.Resource;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import gui.TestGUI;
public class Tile implements MouseListener
{
  public enum Type {GRASS_TILE,GROUND_TILE,SAND_TILE,
                        WATER_TILE,ROCK_TILE,ERROR_TILE};
  
  private final Resource resource;
  private final JLabel icon = new JLabel();
  private final String name; 

  public Tile(Type typeOfTerrain)
  {
    switch (typeOfTerrain) 
    {
      case GRASS_TILE:icon.setIcon( new ImageIcon(
                      getClass().getResource("../assets/Tiles/GrassTile.png")));
                      resource = generateTileResources(Resource.Type.SULFUR,25,
                                                       Resource.Type.COWS,50,
                                                       Resource.Type.NOTHING);
                      name = "Grass Tile";
                      break;
      case GROUND_TILE:icon.setIcon( new ImageIcon(
                    getClass().getResource("../assets/Tiles/GroundTile.png")));
                       resource = generateTileResources(Resource.Type.COAL,60,
                                                        Resource.Type.IRON,30,
                                                        Resource.Type.OIL);
                       name = "Ground Tile";
                       break;
      case SAND_TILE:icon.setIcon( new ImageIcon(
                      getClass().getResource("../assets/Tiles/SandTile.png")));
                     resource = generateTileResources(Resource.Type.OIL,40,
                                                      Resource.Type.NOTHING);
                     name = "Sand Tile";
                     break;
      case WATER_TILE:icon.setIcon( new ImageIcon(
                      getClass().getResource("../assets/Tiles/WaterTile.png")));
                      resource = generateTileResources(Resource.Type.OIL,30,
                                                       Resource.Type.FISH,30,
                                                       Resource.Type.NOTHING);
                      name = "Water Tile";
                      break;
      case ROCK_TILE:icon.setIcon( new ImageIcon(
                     getClass().getResource("../assets/Tiles/RockTile.png")));
                     resource = generateTileResources(Resource.Type.COAL,25,
                                           Resource.Type.IRON);
                     name = "Mountain Tile";
                     break;
      default: icon.setIcon( new ImageIcon( 
                        getClass().getResource("../assets/Tiles/Default.png")));
               resource = generateTileResources(Resource.Type.ERROR);
               name = "Error";
              break;
    }
    icon.addMouseListener(this);
  }//constuctor
  /**
   * Generates the tile's resources based on what resources are able to be 
   * there, and what's the percentage of said resources to spawn.
   * percent1 and percent2 must add up to less than 100 and percent1 
   * must always be smaller or equal to percent2(i.e. res1 is rarer than 
   * res2 which is rarer than res3)
   * @param res1     The first possible resource
   * @param percent1 The percentage for res1 to spawn,is between 0 and 100
   * @param res2     The second possible resource
   * @param percent2 The percentage for res2 to spawn,is between 0 and 100
   * @param res3     The third possible resource,is put in place if neither
   * res1 or res2 made it on the Tile.
   */
   private Resource generateTileResources(Resource.Type res1,int percent1,
                                      Resource.Type res2,int percent2,
                                      Resource.Type res3)
  {
    
    if(MyMath.hasPercentToHappen(percent1))
      return new Resource(res1,MyMath.roundedRandomValue(res1.getMaxValue()));
    else if (MyMath.hasPercentToHappen(percent2)) 
      return new Resource(res2,MyMath.roundedRandomValue(res2.getMaxValue())); 
    else 
      return new Resource(res3,MyMath.roundedRandomValue(res3.getMaxValue()));
    
  }//generateTileResources
  
  /**
   * Works just like {@link #generateTileResources(Resource.Type,int,
   *                                                        Resource.Type,int,
   *                                                        Resource.Type)} 
   * except it takes in less resources. 
   * @see Tile#generateTileResources(Resource.Type,int,
   *                                 Resource.Type,int,
   *                                 Resource.Type)
   */
  private Resource generateTileResources(Resource.Type res1,int percent1,
                                     Resource.Type res2)
  {
    
    if(MyMath.hasPercentToHappen(percent1))
      return new Resource(res1,MyMath.roundedRandomValue(res1.getMaxValue()));
    else 
      return new Resource(res2,MyMath.roundedRandomValue(res2.getMaxValue()));   
  }//generateTileResources

  /**
   * Works just like {@link #generateTileResources(Resource.Type,int,
   *                                                        Resource.Type,int,
   *                                                        Resource.Type)} 
   * except it takes in less resources. 
   * @see Tile#generateTileResources(Resource.Type,int,
   *                                 Resource.Type,int,
   *                                 Resource.Type)
   */
  private Resource generateTileResources(Resource.Type res)
  {
    return new Resource(res,MyMath.roundedRandomValue(res.getMaxValue()));  
  }//generateTileResources

  public String getResourceName()
  {
    return resource.getResourceName();
  }//resource accesor

  public JLabel getIcon()
  {
    return icon;
  }//icon accesor

  public String getName()
  {
    return name;
  }//tile name accesor

  public int getValue()
  {
    return resource.getValue();
  }//resource value accesor

  public void mouseEntered(MouseEvent e) 
  {
    TestGUI.randomName.setTileInfoPanel(this);
  }

  public void mouseClicked(MouseEvent e) {}

  public void mousePressed(MouseEvent e) {}

  public void mouseReleased(MouseEvent e) {}

  public void mouseExited(MouseEvent e) {}

}