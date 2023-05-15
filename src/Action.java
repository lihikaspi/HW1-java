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
        if (this.direction == Direction.UP) direction = "up";
        else if (this.direction == Direction.DOWN) direction = "down";
        else if (this.direction == Direction.RIGHT) direction = "right";
        else if (this.direction == Direction.LEFT) direction = "left";
       return ("Move " + tile.getValue() + " " + direction); // example: Move 7 down
    }

    public void moveTile(Board board) {
        Tile[] tiles = board.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].getValue() == 0) {
                if (this.direction == Direction.RIGHT) tiles[i+1] = tiles[i];
                if (this.direction == Direction.LEFT) tiles[i-1] = tiles[i];
                if (this.direction == Direction.UP) tiles[i-board.getRow()] = tiles[i];
                if (this.direction == Direction.DOWN) tiles[i+board.getRow()] = tiles[i];
                tiles[i] = this.tile;
            }
        }
    }
}
