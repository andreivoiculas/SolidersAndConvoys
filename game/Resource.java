package game;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
public class Resource 
{
  public enum Type 
  {
    WOOD(100),IRON(100),COAL(100),OIL(100),
    FISH(100),SULFUR(100),COWS(100),NOTHING(0),ERROR(0);
    private final int maxValue;
    private Type(int maxValue)
    {
      this.maxValue = maxValue;
    }
    public int getMaxValue()
    {
      return maxValue;
    }
  }
  private final String name;
  private final int value;
  private final ImageIcon icon;
  private final Type resType;
  public Resource(Type resType,int value)
  {
    switch (resType) 
    {
      case WOOD: name = "Wood";
                 this.value = value;
                 icon = new ImageIcon(
                  getClass().getResource("../assets/Resources/Default.png"));
                 this.resType = resType;
                 break;
      case IRON: name = "Iron";
                 this.value = value;
                 icon = new ImageIcon(
                  getClass().getResource("../assets/Resources/Default.png"));
                 this.resType = resType;
                 break;
      case COAL: name = "Coal";
                 this.value = value;
                 icon = new ImageIcon(
                  getClass().getResource("../assets/Resources/Default.png"));
                 this.resType = resType;
                 break;
      case OIL: name = "Oil";
                 this.value = value;
                 icon = new ImageIcon(
                  getClass().getResource("../assets/Resources/Default.png"));
                 this.resType = resType;
                 break;
      case FISH: name = "Fish";
                 this.value = value;
                 icon = new ImageIcon(
                  getClass().getResource("../assets/Resources/Default.png"));
                 this.resType = resType;
                 break;
      case COWS: name = "Cattle";
                 this.value = value;
                 icon = new ImageIcon(
                  getClass().getResource("../assets/Resources/Default.png"));
                 this.resType = resType;
                 break;
      case SULFUR: name = "Sulfur";
                 this.value = value;
                 icon = new ImageIcon(
                  getClass().getResource("../assets/Resources/Default.png"));
                 this.resType = resType;
                 break;
      case NOTHING: name = "No resource";
                 this.value = value;
                 icon = new ImageIcon(
                  getClass().getResource("../assets/Resources/Default.png"));
                 this.resType = resType;
                 break;
      default: System.out.println("The is no resource" + 
                                          " that matches description.");
               name = "NullName";
               this.value = 0;
               icon = new ImageIcon(
                getClass().getResource("../assets/Resources/Default.png"));
               this.resType = Type.ERROR;
               break;
    }//switch
  }//constuctor
  public String getResourceName()
  {
    return name; 
  }

  public ImageIcon getResourceIcon()
  {
    return icon; 
  }

  public int getValue()
  {
    return value;
  }
}