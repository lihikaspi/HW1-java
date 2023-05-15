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
        Tile[][] tiles = board.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].getValue() == 0) {
                    if (this.direction == Direction.RIGHT) {
                        board.swapTiles(i, j, i, j-1);
                        return;
                    } else if (this.direction == Direction.LEFT) {
                        board.swapTiles(i, j, i, j+1);
                        return;
                    } else if (this.direction == Direction.UP) {
                        board.swapTiles(i, j, i+1, j);
                        return;
                    } else if (this.direction == Direction.DOWN) {
                        board.swapTiles(i, j, i-1, j);
                        return;
                    }
                }
            }
        }
    }
}
