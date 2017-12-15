set -v
cd myUtil
javac -cp .. -d .. MyMath.java
cd ../game
javac -cp .. -d .. Resource.java
javac -cp .. -d .. Tile.java
javac -cp .. -d .. Map.java
cd ../gui
javac -cp .. -d .. TileInfoPanel.java
javac -cp .. -d .. MapGUI.java
javac -cp .. -d .. TestGUI.java
cd ..