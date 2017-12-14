package game;
import game.Tile;
import myUtil.MyMath;

public  class Map
{
  enum Type {ISLAND,MOUNTAIN,DESERT}
  private int length;
  private int heigth;
  private Tile[][] tileMap[heigth][length];

  public Map(Type mapType)
  {
    this.length = length;
    this.heigth = heigth;
    switch (mapType) 
    {
      case ISLAND: islandMap();
                   break; 
    }
  }
  private void islandMap()
  {
    for (int row = 0;row < length ;row++ )
      for (int column = 0;column < heigth ;column++ ) 
      {
        tileMap[row][column] = new Tile(WATER_TILE);  
      }
  }
  private void smallIsland()
  {
    int x,y;
    //while island would be generated out of the map,choose another x and y
    while (x < 2 || x >= length-2 || y >= heigth - 4)
    {
      x = MyMath.roundedRandomValue(length);
      y = MyMath.roundedRandomValue(heigth);
    }
      
    }
  }
}