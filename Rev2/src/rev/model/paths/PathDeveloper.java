package rev.model.paths;

import java.util.List;

import rev.model.data.MotionOperation;
import rev.model.map.core.Tile;
/**
 * 
 * @author laurencegoldinger
 *
 */
public interface PathDeveloper {

    /**
     * 
     * @param origin
     * 				The origin tile of the path
     * @param destination
     * 				The destination tile of the path
     * @return
     * 				The path from origin to destination in which all motions only occur over land to land motions
     */
	public List<MotionOperation> getLandPathBetween(Tile origin, Tile destination);
    
    /**
     * 
     * @param origin
     * 				The origin tile of the path
     * @param destination
     * 				The destination tile of the path
     * @return
     * 				The path from origin to destination in which all motions only occur over water to water motions
     */
    public List<MotionOperation> getWaterPathBetween(Tile origin, Tile destination);
    
    /**
     * 
     * @param origin
     * 				The origin tile of the path
     * @param destination
     * 				The destination tile of the path
     * @param maxHeight
     * 				The maximum height that the path can pass through
     * @return
     * 				The path from origin to destination in which all motions only occur over land to land motions that are height restricted
     */
    public List<MotionOperation> getHeightPathBetween(Tile origin, Tile destination, int maxHeight);
    
    /**
     * 
     * @param origin
     * 				The origin tile of the path
     * @param destination
     * 				The destination tile of the path
     * @return
     * 				The path from origin to destination in which all motions only occur over land tiles
     */
    public List<Tile> getLandTilePathBetween(Tile origin, Tile destination);
    
    /**
     * 
     * @param origin
     * 				The origin tile of the path
     * @param destination
     * 				The destination tile of the path
     * @return
     * 				The path from origin to destination in which all motions only occur over water tiles
     */
    public List<Tile> getWaterTilePathBetween(Tile origin, Tile destination);

    /**
     * 
     * @param origin
     * 				The origin tile of the path
     * @param destination
     * 				The destination tile of the path
     * @param maxHeight
     * 				The maximum height that the path can pass through
     * @return
     * 				The path from origin to destination in which all motions only occur tiles that have height less than maxHeight
     */
    public List<Tile> getHeightTilePathBetween(Tile origin, Tile destination, int maxHeight);
    
    /**
     * 
     * @param first
     * @param second
     * @return
     * 				Determines if the first and second tiles have a possible path between them without changing domains
     */
    public boolean sameSet(Tile first, Tile second) ;
}