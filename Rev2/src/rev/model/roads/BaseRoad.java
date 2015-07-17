package rev.model.roads;

import rev.model.attributes.Position;

public class BaseRoad implements Road {

    private final Position position;

    private boolean usable = true;
    
    public BaseRoad(int x, int y) {
        this.position = new Position(x, y);
    }

    @Override
    public Integer getX() {
        return this.position.getX();
    }

    @Override
    public Integer getY() {
        return this.position.getY();
    }

    @Override
    public Boolean isUsable() {
        return this.usable;
    }
}