public class Action {
    private final Tile tile;
    private final Direction direction;

    public Action(Tile tile, Direction direction){
        this.tile = tile;
        this.direction = direction;
    }

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

    public Direction getDirection() {
        return direction;
    }
}
