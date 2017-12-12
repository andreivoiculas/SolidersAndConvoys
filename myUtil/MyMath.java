package myUtil;
public class MyMath
{
  /** 
  * Says if event will or will not happen 
  * @param percent Likelyhood of event happening
  * @return True if event happens,false if not
  */
  public static boolean hasPercentToHappen( int percent) 
  {
    if( percent/100.0 - Math.random() < 0)
      return false;
    else
      return true;
  }
  /**
   * @param maxValue The maximum value your random number should take
   * @return An integer between 0 and maxValue
   */
  public static int roundedRandomValue(int maxValue)
  {
   return Math.round((float)Math.random()*maxValue);
  }


}