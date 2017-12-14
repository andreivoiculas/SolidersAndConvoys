package game;
import game.Tile;
import myUtil.MyMath;
import javax.swing.JFrame;

public  class Map
{
  /**
   * The Type of the map.It instructs the constructor to generate a different
   * map comositions.
   */
  public enum Type {ISLAND,MOUNTAIN,DESERT}
  /**
   * The length of the map
   */
  private int length;
  /**
   * The heigth of the map
   */
  private int heigth;
  /**
   * The matrix of the map,everything related to logic about what's on the
   * map uses this variable
   */
  private Tile[][] tileMap;
  /**
   * Keeps track of all the tiles that are non-default 
   * (e.g. WATER_TILE for ISLAND mapType).
   */
  private int filled = 0;
  /**
   * A number between 0 and 1 which tracks the percent of tiles which are 
   * non-default for the mapType (e.g. WATER_TILE for ISLAND mapType).
   * Used to determine if generation of different map components continues
   */
  private float filledRaport = 0;


  public Map(Type mapType,int length,int heigth)
  {
    this.length = length;
    this.heigth = heigth;
    tileMap =  new Tile[heigth][length];
    switch (mapType) 
    {
      case ISLAND: islandMap();
                   break; 
      case MOUNTAIN: break;
      case DESERT: break;
      default: break;
    }
  }
  private void islandMap()
  {
    for (int row = 0;row < length ;row++ )
      for (int column = 0;column < heigth ;column++ ) 
      {
        tileMap[row][column] = new Tile(Tile.Type.WATER_TILE);  
      }
    while(filledRaport < 0.4)
    {
      if(MyMath.hasPercentToHappen(40))
        bigIsland();
      else
        smallIsland();
      filledRaport = filled/length*heigth;
    }
  }
  /**
   *Checks for a legal place and creates a small islad of format:
   *            sand
   *      grass grass grass      
   * sand grass rock  grass sand
   *      grass grass grass
   *            sand 
   */
  private void smallIsland()
  {
    int x = 0,y = 0;//coordinates of middle point
    filled += 13;
    boolean isOutsideMap = x -2 < 0 || x - 2 >= length 
                          || y + 2 >= heigth || y - 2 < 0,
            isInsideOtherIsland = tileMap[y+2][x-2].getType() 
                              != Tile.Type.WATER_TILE
                              || tileMap[y][x].getType() 
                              != Tile.Type.WATER_TILE
                              || tileMap[y][x].getType() 
                              != Tile.Type.WATER_TILE
                              || tileMap[y+4][x].getType() 
                              != Tile.Type.WATER_TILE
                              || tileMap[y+2][x+2].getType() 
                              != Tile.Type.WATER_TILE;
    while (isOutsideMap || isInsideOtherIsland)
    {
      x = MyMath.roundedRandomValue(length);
      y = MyMath.roundedRandomValue(heigth);
    }
    tileMap[y][x] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y+1][x] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y+2][x] = new Tile(Tile.Type.ROCK_TILE);
    tileMap[y+3][x] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y+4][x] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y+1][x-1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y+2][x-1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y+3][x-1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y+1][x+1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y+2][x+1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y+3][x+1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y+2][x+2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y+2][x-2] = new Tile(Tile.Type.GRASS_TILE);
  }//smallIsland

  /**
   * Checks for a legal place and creates a small islad of format:
   *             sand   sand   sand
   *       grass grass  grass  grass  grass
   *  sand grass ground ground ground grass sand
   *  sand grass ground rock   ground grass sand
   *  sand grass rock   rock   rock   grass sand
   *  sand grass ground rock   ground grass sand
   *  sand grass ground ground ground grass sand
   *       grass grass  grass  grass  grass
   *             sand   sand   sand
   *  
   */
  private void bigIsland()
  {
    int x = 0,y = 0;//coordinates of middle point
    filled += 65;
    boolean isOutsideMap = x - 3 < 0 || x + 3 >= length 
                          || y - 4 < 0 || y + 4 >= heigth,
            isInsideOtherIsland = tileMap[y -4][x -1].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y - 4][x].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y - 4][x + 1].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y + 4][x - 1].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y + 4][x].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y + 4][x + 1].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y - 2][x - 3].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y - 1][x - 3].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y][x - 3].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y + 1][x - 3].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y + 2][x - 3].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y - 2][x + 3].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y - 2][x + 3].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y - 1][x + 3].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y][x + 3].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y + 1][x + 3].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y + 2][x + 3].getType() 
                                  != Tile.Type.WATER_TILE
                                  || tileMap[y - 2][x + 3].getType() 
                                  != Tile.Type.WATER_TILE;
    while (isOutsideMap || isInsideOtherIsland)
    {
      x = MyMath.roundedRandomValue(length);
      y = MyMath.roundedRandomValue(heigth);
    }
    tileMap[x - 4][y -1] = new Tile(Tile.Type.SAND_TILE);
    tileMap[x - 4][y] = new Tile(Tile.Type.SAND_TILE);
    tileMap[x - 4][y + 1] = new Tile(Tile.Type.SAND_TILE);
    tileMap[x - 3][y - 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x - 3][y - 1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x - 3][y] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x - 3][y + 1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x - 3][y + 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x - 2][y - 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[x - 2][y - 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x - 2][y - 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[x - 2][y] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[x - 2][y + 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[x - 2][y + 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x - 2][y + 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[x - 1][y - 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[x - 1][y - 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x - 1][y - 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[x - 1][y] = new Tile(Tile.Type.ROCK_TILE);
    tileMap[x - 1][y + 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[x - 1][y + 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x - 1][y + 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[x][y - 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[x][y - 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x][y - 1] = new Tile(Tile.Type.ROCK_TILE);
    tileMap[x][y] = new Tile(Tile.Type.ROCK_TILE);
    tileMap[x][y + 1] = new Tile(Tile.Type.ROCK_TILE);
    tileMap[x][y + 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x][y + 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[x + 1][y - 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[x + 1][y - 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x + 1][y - 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[x + 1][y] = new Tile(Tile.Type.ROCK_TILE);
    tileMap[x + 1][y + 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[x + 1][y + 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x + 1][y + 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[x + 2][y - 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[x + 2][y - 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x + 2][y - 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[x + 2][y] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[x + 2][y + 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[x + 2][y + 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x + 2][y + 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[x + 3][y - 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x + 3][y - 1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x + 3][y] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x + 3][y + 1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x + 3][y + 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[x + 4][y -1] = new Tile(Tile.Type.SAND_TILE);
    tileMap[x + 4][y] = new Tile(Tile.Type.SAND_TILE);
    tileMap[x + 4][y + 1] = new Tile(Tile.Type.SAND_TILE);
  }//bigIsland

  public int getLenght()
  {
    return length;
  }//accesor getLenght

  public int getHeight()
  {
    return heigth;
  }//accesor getHeight

  public Tile[][] getMapTiles()
  {
    return tileMap;   
  }//getMapIcon
}