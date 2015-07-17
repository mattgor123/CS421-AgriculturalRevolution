package rev.model.paths;
/**
 * 
 * Base implementation of PathDeveloper
 * 
 * @author laurencegoldinger
 */

import java.util.LinkedList;
import java.util.List;

import rev.model.data.BaseMotionOperation;
import rev.model.data.MotionOperation;
import rev.model.map.core.Tile;
import rev.model.map.core.TileMap;

import rev.model.map.core.TileDomain;

public class BasePathDeveloper implements PathDeveloper {

    private TileMap map;
    private LinkedList<LinkedList<Tile>> disjointTerra = new LinkedList<LinkedList<Tile>>();
    private LinkedList<LinkedList<Tile>> disjointWater = new LinkedList<LinkedList<Tile>>();
    public BasePathDeveloper(TileMap map) {
        this.map = map;
        //Go through every tile, if the tile is in an existing set ignore it, if it isn't develop a proper(water/land) set around it
        for(int i = 0; i < map.getWidth(); i++) {
        	for(int j = 0; j < map.getHeight(); j++) {
        		if(map.getTile(i, j).getDomain() == TileDomain.TERRA) {
        			boolean exists = false;
        			for(LinkedList<Tile> set : disjointTerra) {
        				if(set.contains(map.getTile(i,j))) {
        					exists = true;
        				}
        			}
        			if(!exists) {
        				DevelopSet(i,j, TileDomain.TERRA, disjointTerra);
        			}
        		}
        		else if(map.getTile(i,j).getDomain() == TileDomain.AQUA) {
        			boolean exists = false;
        			for(LinkedList<Tile> set : disjointWater) {
        				if(set.contains(map.getTile(i,j))) {
        					exists = true;
        				}
        			}
        			if(!exists) {
        				DevelopSet(i,j, TileDomain.AQUA, disjointWater);
        			}
        			
        		}
        	}
        }
    }

    private void DevelopSet(int x, int y, TileDomain domain, LinkedList<LinkedList<Tile>> disjoint) {
		LinkedList<Tile> set = new LinkedList<Tile>();
		boolean inSet[][] = new boolean[map.getWidth()][map.getHeight()];
		inSet[x][y] = true;
		boolean changed = true;
		//The coordinate we are given are in the set by default
		//Infect the surrounding tiles that are in the given domain until you can no longer reach a connecting tile in the domain
		while(changed) {
			changed = false;
			for(int i = 0; i < map.getWidth(); i++) {
				for(int j = 0; j < map.getHeight(); j++) {
					if(inSet[i][j] == true) {
						if(i > 0 && map.getTile(i-1,j).getDomain() == domain && inSet[i-1][j] != true) {
							changed = true;
							inSet[i-1][j] = true;
						}
						if(i < map.getWidth()-1 && map.getTile(i+1,j).getDomain() == domain && inSet[i+1][j] != true) {
							changed = true;
							inSet[i+1][j] = true;
						}
						if(j > 0 && map.getTile(i,j-1).getDomain() == domain && inSet[i][j-1] != true) {
							changed = true;
							inSet[i][j-1] = true;
						}
						if(j < map.getHeight()-1 && map.getTile(i,j+1).getDomain() == domain && inSet[i][j+1] != true) {
							changed = true;
							inSet[i][j+1] = true;
						}
					}
				}
			}
		}
		//Now that we know the coordinates in the set add the tiles in a list
		for(int i = 0; i < map.getWidth(); i++) {
			for(int j = 0; j < map.getHeight(); j++) {
				if(inSet[i][j]) {
					set.addLast(map.getTile(i,j));
				}
			}
		}
			disjoint.addLast(set);
		
		
	}
    public List<Tile> getLandTilePathBetween(Tile origin, Tile destination) {
    	//Get the motion directed path 
    	List<MotionOperation> path = getLandPathBetween(origin, destination);
    	LinkedList<Tile> result = new LinkedList<Tile>();
    	if (path!=null ) {
    		if(path.isEmpty()) {
    			//if we get an empty path return an empty path
    			return result;
    		}
    		//Otherwise there is repetition between the origin of each motion and the destination of the next
    		//The only destination that isn't repeated is the last one
    		MotionOperation last = path.get(path.size()-1);
    		for(MotionOperation x : path) {
    			result.addLast(x.getOrigin());
    			}
    		result.addLast(last.getDestination());
    		return result;
    	}
    	else {
    		return null;
    	}
    }
    //Similar to land path but adds the condition that the elevation must be below a certain threshold
    public List<Tile> getHeightTilePathBetween(Tile origin, Tile destination, int maxHeight) {
    	List<MotionOperation> path = getHeightPathBetween(origin, destination, maxHeight);
    	LinkedList<Tile> result = new LinkedList<Tile>();
    	if (path!=null) {
    		if(path.isEmpty()) {
    			return result;
    		}
    		MotionOperation last = path.get(path.size()-1);
    		for(MotionOperation x : path) {
    			result.addLast(x.getOrigin());
    			}
    		result.addLast(last.getDestination());
    		return result;
    	}
    	else {
    		return null;
    	}
    }
    //Similar to land tile path but the domain is water
    public List<Tile> getWaterTilePathBetween(Tile origin, Tile destination) {
    	List<MotionOperation> path = getWaterPathBetween(origin, destination);
    	LinkedList<Tile> result = new LinkedList<Tile>();
    	if (path != null) {
    		if(path.isEmpty()) {
    			return result;
    		}
    		MotionOperation last = path.get(path.size()-1);
    		for(MotionOperation x : path) {
    			result.addLast(x.getOrigin());
    			}
    		result.addLast(last.getDestination());
    		return result;
    	}
    	else {
    		return null;
    	}
    }
	@Override
    public List<MotionOperation> getLandPathBetween(Tile origin, Tile destination) {
		//If either tile is in the wrong domain return a null path
		if(origin.getDomain() == TileDomain.AQUA || destination.getDomain() == TileDomain.AQUA) {
			return null;
		}
		if(origin == destination) {
			//Empty path returns an empty path
			return new LinkedList<MotionOperation>();
		}
		boolean sameSet = false;
		//Make sure that both of the tiles are in the same set before attempting to find a path
		for(List<Tile> set : disjointTerra) {
			if(set.contains(origin) && set.contains(destination)) {
				sameSet = true;
			}
		}
		if (sameSet)
		{
			return CalcTerraPath(origin, destination);
		}
		else {
			return null;
		}
    }
	//Similar to land path with the height restriction
	 public List<MotionOperation> getHeightPathBetween(Tile origin, Tile destination, int maxHeight) {
			if(origin.getDomain() == TileDomain.AQUA || origin.getElevation() > maxHeight || destination.getDomain() == TileDomain.AQUA || destination.getElevation() > maxHeight) {
				return null;
			}
			if(origin == destination) {
				return new LinkedList<MotionOperation>();
			}
			boolean sameSet = false;
			for(List<Tile> set : disjointTerra) {
				if(set.contains(origin) && set.contains(destination)) {
					sameSet = true;
				}
			}
			if (sameSet)
			{
				return CalcHeightPath(origin, destination, maxHeight);
			}
			else {
				return null;
			}
	    }

	private List<MotionOperation> CalcTerraPath(Tile origin, Tile destination) {
		Integer distance[][] = new Integer[map.getWidth()][map.getHeight()];
		distance[origin.getX()][origin.getY()] = 0;
		boolean changed = true;
		//Infect the surrounding tiles to find the distance, this method may be inefficient for the current single path weight in some cases but is set up to allow for variable weight movement
		while(changed) {
			changed = false;
			for(int i = 0; i < map.getWidth(); i++) {
				for(int j = 0; j < map.getHeight(); j++) {
					if(distance[i][j] != null) {
						if(i > 0 && (distance[i-1][j] == null || distance[i-1][j] > distance[i][j] + 1)&& map.getTile(i-1, j).getDomain() == TileDomain.TERRA) {
							distance[i-1][j] = distance[i][j] + 1;
							changed = true;
						}
						if(i < map.getWidth() - 1 && (distance[i+1][j] == null || distance[i+1][j] > distance[i][j] + 1)&& map.getTile(i+1, j).getDomain() == TileDomain.TERRA) {
							distance[i+1][j] = distance[i][j] + 1;
							changed = true;
						}
						if(j > 0 && (distance[i][j-1] == null || distance[i][j-1] > distance[i][j] + 1)&& map.getTile(i, j-1).getDomain() == TileDomain.TERRA) {
							distance[i][j-1] = distance[i][j] + 1;
							changed = true;
						}
						if(j < map.getHeight() - 1 && (distance[i][j+1] == null || distance[i][j+1] > distance[i][j] + 1)&& map.getTile(i, j+1).getDomain() == TileDomain.TERRA) {
							distance[i][j+1] = distance[i][j] + 1;
							changed = true;
						}
					}
				}
			}
		}
		int x = destination.getX();
		int y = destination.getY();
		int distanceFrom = distance[x][y];
		//Create a path based on the weight on the destination tile
		LinkedList<MotionOperation> path = new LinkedList<MotionOperation>();
		while(distanceFrom > 0){
			if(x > 0 && distance[x-1][y] != null && distance[x-1][y] == distanceFrom - 1) {
				x -= 1;
				distanceFrom -= 1;
				path.addFirst(new BaseMotionOperation(map.getTile(x, y), map.getTile(x+1, y)));
			}
			else if(x < map.getWidth()-1 && distance[x+1][y] != null && distance[x+1][y] == distanceFrom - 1) {
				x += 1;
				distanceFrom -= 1;
				path.addFirst(new BaseMotionOperation(map.getTile(x, y), map.getTile(x-1, y)));
			}
			else if(y > 0 && distance[x][y-1] != null && distance[x][y-1] == distanceFrom - 1) {
				y -= 1;
				distanceFrom -= 1;
				path.addFirst(new BaseMotionOperation(map.getTile(x, y), map.getTile(x, y+1)));
			}
			else if(y < map.getHeight()-1 && distance[x][y+1] != null && distance[x][y+1] == distanceFrom - 1) {
				y += 1;
				distanceFrom -= 1;
				path.addFirst(new BaseMotionOperation(map.getTile(x, y), map.getTile(x, y-1)));
			}
		}
		return path;
	}
	//Similar to land path, one important difference
	private List<MotionOperation> CalcHeightPath(Tile origin, Tile destination, int maxHeight) {
		Integer distance[][] = new Integer[map.getWidth()][map.getHeight()];
		distance[origin.getX()][origin.getY()] = 0;
		boolean changed = true;
		while(changed) {
			changed = false;
			for(int i = 0; i < map.getWidth(); i++) {
				for(int j = 0; j < map.getHeight(); j++) {
					if(distance[i][j] != null) {
						if(i > 0 && (distance[i-1][j] == null || distance[i-1][j] > distance[i][j] + 1) && map.getTile(i-1,j).getElevation() <= maxHeight && map.getTile(i-1, j).getDomain() == TileDomain.TERRA) {
							distance[i-1][j] = distance[i][j] + 1;
							changed = true;
						}
						if(i < map.getWidth() - 1 && (distance[i+1][j] == null || distance[i+1][j] > distance[i][j] + 1) && map.getTile(i+1,j).getElevation() <= maxHeight && map.getTile(i+1, j).getDomain() == TileDomain.TERRA) {
							distance[i+1][j] = distance[i][j] + 1;
							changed = true;
						}
						if(j > 0 && (distance[i][j-1] == null || distance[i][j-1] > distance[i][j] + 1) && map.getTile(i,j-1).getElevation() <= maxHeight && map.getTile(i, j-1).getDomain() == TileDomain.TERRA) {
							distance[i][j-1] = distance[i][j] + 1;
							changed = true;
						}
						if(j < map.getHeight() - 1 && (distance[i][j+1] == null || distance[i][j+1] > distance[i][j] + 1) && map.getTile(i,j+1).getElevation() <= maxHeight && map.getTile(i, j+1).getDomain() == TileDomain.TERRA) {
							distance[i][j+1] = distance[i][j] + 1;
							changed = true;
						}
					}
				}
			}
		}
		int x = destination.getX();
		int y = destination.getY();
		if(distance[x][y] == null) {
			//Its possible for there to be no path even if the disjoint sets are the same, therefore we need a check
			return null;
		}
		int distanceFrom = distance[x][y];
		LinkedList<MotionOperation> path = new LinkedList<MotionOperation>();
		while(distanceFrom > 0){
			if(x > 0 && distance[x-1][y] != null && distance[x-1][y] == distanceFrom - 1) {
				x -= 1;
				distanceFrom -= 1;
				path.addFirst(new BaseMotionOperation(map.getTile(x, y), map.getTile(x+1, y)));
			}
			else if(x < map.getWidth()-1 && distance[x+1][y] != null && distance[x+1][y] == distanceFrom - 1) {
				x += 1;
				distanceFrom -= 1;
				path.addFirst(new BaseMotionOperation(map.getTile(x, y), map.getTile(x-1, y)));
			}
			else if(y > 0 && distance[x][y-1] != null && distance[x][y-1] == distanceFrom - 1) {
				y -= 1;
				distanceFrom -= 1;
				path.addFirst(new BaseMotionOperation(map.getTile(x, y), map.getTile(x, y+1)));
			}
			else if(y < map.getHeight()-1 && distance[x][y+1] != null && distance[x][y+1] == distanceFrom - 1) {
				y += 1;
				distanceFrom -= 1;
				path.addFirst(new BaseMotionOperation(map.getTile(x, y), map.getTile(x, y-1)));
			}
		}
		return path;
	}
	//Similar to land path
	public List<MotionOperation> getWaterPathBetween(Tile origin, Tile destination) {
		if(origin.getDomain() == TileDomain.TERRA || destination.getDomain() == TileDomain.TERRA) {
			return null;
		}
		if(origin == destination) {
			return new LinkedList<MotionOperation>();
		}
		boolean sameSet = false;
		for(List<Tile> set : disjointWater) {
			if(set.contains(origin) && set.contains(destination)) {
				sameSet = true;
			}
		}
		if (sameSet)
		{
			return CalcWaterPath(origin, destination);
		}
		else {
			return null;
		}
    }

	//Similar to land path
	private List<MotionOperation> CalcWaterPath(Tile origin, Tile destination) {
		Integer distance[][] = new Integer[map.getWidth()][map.getHeight()];
		distance[origin.getX()][origin.getY()] = 0;
		boolean changed = true;
		while(changed) {
			changed = false;
			for(int i = 0; i < map.getWidth(); i++) {
				for(int j = 0; j < map.getHeight(); j++) {
					if(distance[i][j] != null) {
						if(i > 0 && (distance[i-1][j] == null || distance[i-1][j] > distance[i][j] + 1) && map.getTile(i-1, j).getDomain() == TileDomain.AQUA) {
							distance[i-1][j] = distance[i][j] + 1;
							changed = true;
						}
						if(i < map.getWidth() - 1 && (distance[i+1][j] == null || distance[i+1][j] > distance[i][j] + 1)&& map.getTile(i+1, j).getDomain() == TileDomain.AQUA) {
							distance[i+1][j] = distance[i][j] + 1;
							changed = true;
						}
						if(j > 0 && (distance[i][j-1] == null || distance[i][j-1] > distance[i][j] + 1)&& map.getTile(i, j-1).getDomain() == TileDomain.AQUA) {
							distance[i][j-1] = distance[i][j] + 1;
							changed = true;
						}
						if(j < map.getHeight() - 1 && (distance[i][j+1] == null || distance[i][j+1] > distance[i][j] + 1)&& map.getTile(i, j +1).getDomain() == TileDomain.AQUA) {
							distance[i][j+1] = distance[i][j] + 1;
							changed = true;
						}
					}
				}
			}
		}
		int x = destination.getX();
		int y = destination.getY();
		int distanceFrom = distance[x][y];
		LinkedList<MotionOperation> path = new LinkedList<MotionOperation>();
		while(distanceFrom > 0){
			if(x > 0 && distance[x-1][y] != null && distance[x-1][y] == distanceFrom - 1) {
				x -= 1;
				distanceFrom -= 1;
				path.addFirst(new BaseMotionOperation(map.getTile(x, y), map.getTile(x+1, y)));
			}
			else if(x < map.getWidth()-1 && distance[x+1][y] != null && distance[x+1][y] == distanceFrom - 1) {
				x += 1;
				distanceFrom -= 1;
				path.addFirst(new BaseMotionOperation(map.getTile(x, y), map.getTile(x-1, y)));
			}
			else if(y > 0 && distance[x][y-1] != null && distance[x][y-1] == distanceFrom - 1) {
				y -= 1;
				distanceFrom -= 1;
				path.addFirst(new BaseMotionOperation(map.getTile(x, y), map.getTile(x, y+1)));
			}
			else if(y < map.getHeight()-1 && distance[x][y+1] != null && distance[x][y+1] == distanceFrom - 1) {
				y += 1;
				distanceFrom -= 1;
				path.addFirst(new BaseMotionOperation(map.getTile(x, y), map.getTile(x, y-1)));
			}
		}
		return path;
	}

	@Override
	public boolean sameSet(Tile first, Tile second) {
		boolean same = false;
		//First check land sets
		for(List<Tile> x : disjointTerra) {
			if ( x.contains(first) && x.contains(second)) {
				same = true;
			}
		}
		//Second check water sets
		for(List<Tile> x : disjointWater) {
			if ( x.contains(first) && x.contains(second)) {
				same = true;
			}
		}
		return same;
	}

}