public class Action {
    private final Tile tile;
    private final Direction direction;

    /**
     * Regular constructor
     * @param tile tile to move
     * @param direction direction to move
     */
    public Action(Tile tile, Direction direction){
        this.tile = tile;
        this.direction = direction;
    }

    /**
     * turns action to String
     * @return String describing current action
     */
    @Override
    public String toString() {
        String direction = null;
        switch (this.direction) {
            case UP:
                direction = "up";

            case DOWN:
                direction = "down";

            case RIGHT:
                direction = "right";

            case LEFT:
                direction = "left";
        }
       return ("Move " + tile.getValue() + " " + direction); // example: Move 7 down
    }

    /**
     * get direction of action
     * @return direction of action
     */
    public Direction getDirection() {
        return direction;
    }
}
