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

    public void moveTile(Board board) {
        Tile[][] tiles = board.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].getValue() == 0) {
                    switch (this.direction) {
                        case RIGHT:
                            board.swapTiles(i, j, i, j-1);
                            return;

                        case LEFT:
                            board.swapTiles(i, j, i, j+1);
                            return;

                        case UP:
                            board.swapTiles(i, j, i+1, j);
                            return;

                        case DOWN:
                            board.swapTiles(i, j, i-1, j);
                            return;
                    }
                }
            }
        }
    }
}
