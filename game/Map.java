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
  /**
   * Constructs a matrix of {@link game.Tile#Tile() Tiles}.
   * @param  mapType defines what map generation algorithm is used.
   * @param  length  The lenght(X-axis) of the matrix
   * @param  heigth  The heigth(Y-axis) of the matrix
   */
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
  }//constructor

  /**
   * islandMap generates a full water tile map,and then populates it with 
   * islands with the help of {@link smallIsland()} and 
   * {@link bigIsland()}
   */
  private void islandMap()
  {
    for (int row = 0;row < heigth ;row++ )
      for (int column = 0;column < length ;column++ ) 
      {
        tileMap[row][column] = new Tile(Tile.Type.WATER_TILE);  
      }
    while(filledRaport <= 0.25)
    {
      if(MyMath.hasPercentToHappen(20))
      {
        bigIsland();
      }
      else
      {
        smallIsland();
      }
      filledRaport = (float)filled/(length*heigth);
      System.out.println(filledRaport + " " + filled);
    }
  }

  /**
   * Checks for a legal place and creates a small islad of format:
   *            sand
   *      grass grass grass      
   * sand grass rock  grass sand
   *      grass grass grass
   *            sand 
   */
  private void smallIsland()
  { 
    //coordinates of random legal point
    int x = MyMath.roundedRandomValueBetween(2,length-3),
        y = MyMath.roundedRandomValueBetween(2,heigth-3);
    filled += 13;
    boolean isInsideOtherIsland = tileMap[y + 2][x].getType() 
                              != Tile.Type.WATER_TILE

                              || tileMap[y - 2][x].getType() 
                              != Tile.Type.WATER_TILE

                              || tileMap[y][x - 2].getType() 
                              != Tile.Type.WATER_TILE

                              || tileMap[y][x + 2].getType() 
                              != Tile.Type.WATER_TILE;
    while (isInsideOtherIsland)
    {
      x = MyMath.roundedRandomValueBetween(2,length-3);
      y = MyMath.roundedRandomValueBetween(2,heigth-3);
      isInsideOtherIsland = tileMap[y + 2][x].getType() 
                            != Tile.Type.WATER_TILE

                            || tileMap[y - 2][x].getType() 
                            != Tile.Type.WATER_TILE

                            || tileMap[y][x - 2].getType() 
                            != Tile.Type.WATER_TILE

                            || tileMap[y][x + 2].getType() 
                            != Tile.Type.WATER_TILE;
    }
    tileMap[y - 2][x] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y - 1][x - 1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y - 1][x] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y - 1][x + 1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y][x - 2] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y][x - 1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y][x] = new Tile(Tile.Type.ROCK_TILE);
    tileMap[y][x + 1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y][x + 2] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y + 1][x - 1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y + 1][x] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y + 1][x + 1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y + 2][x] = new Tile(Tile.Type.SAND_TILE);
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
   */
  private void bigIsland()
  {
    //coordinates of random legal point
    int x = MyMath.roundedRandomValueBetween(3,length-4),
        y = MyMath.roundedRandomValueBetween(4,heigth-5);
    filled += 65;
    boolean isInsideOtherIsland = tileMap[y - 4][x -1].getType() 
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
    while (isInsideOtherIsland)
    {
       
      x = MyMath.roundedRandomValueBetween(3,length - 4);
      y = MyMath.roundedRandomValueBetween(4,heigth - 5);
      isInsideOtherIsland = tileMap[y - 4][x -1].getType() 
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
    }
    tileMap[y - 4][x -1] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y - 4][x] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y - 4][x + 1] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y - 3][x - 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y - 3][x - 1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y - 3][x] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y - 3][x + 1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y - 3][x + 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y - 2][x - 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y - 2][x - 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y - 2][x - 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[y - 2][x] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[y - 2][x + 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[y - 2][x + 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y - 2][x + 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y - 1][x - 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y - 1][x - 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y - 1][x - 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[y - 1][x] = new Tile(Tile.Type.ROCK_TILE);
    tileMap[y - 1][x + 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[y - 1][x + 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y - 1][x + 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y][x - 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y][x - 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y][x - 1] = new Tile(Tile.Type.ROCK_TILE);
    tileMap[y][x] = new Tile(Tile.Type.ROCK_TILE);
    tileMap[y][x + 1] = new Tile(Tile.Type.ROCK_TILE);
    tileMap[y][x + 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y][x + 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y + 1][x - 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y + 1][x - 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y + 1][x - 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[y + 1][x] = new Tile(Tile.Type.ROCK_TILE);
    tileMap[y + 1][x + 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[y + 1][x + 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y + 1][x + 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y + 2][x - 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y + 2][x - 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y + 2][x - 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[y + 2][x] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[y + 2][x + 1] = new Tile(Tile.Type.GROUND_TILE);
    tileMap[y + 2][x + 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y + 2][x + 3] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y + 3][x - 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y + 3][x - 1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y + 3][x] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y + 3][x + 1] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y + 3][x + 2] = new Tile(Tile.Type.GRASS_TILE);
    tileMap[y + 4][x -1] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y + 4][x] = new Tile(Tile.Type.SAND_TILE);
    tileMap[y + 4][x + 1] = new Tile(Tile.Type.SAND_TILE);
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