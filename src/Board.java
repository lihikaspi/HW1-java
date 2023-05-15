import java.util.Arrays;

public class Board {
    private Tile[][] tiles;
    private int numOfRows;
    private int numOfCols;

    public Board(String boardString) {
        String[] splitIntoRows = boardString.split("\\|");
        this.numOfRows = splitIntoRows.length;
        String[][] splitRow = new String[numOfRows][];
        for (int i = 0; i < splitIntoRows.length; i++) {
             splitRow[i] = splitIntoRows[i].split(" ");
        }
        this.numOfCols = splitRow[0].length;
        this.tiles = new Tile[numOfRows][numOfCols];
        for (int i = 0; i < splitRow.length; i++) {
            for (int j = 0; j < splitRow[i].length; j++) {
                if (splitRow[i][j].equals("_")) this.tiles[i][j] = new Tile(); // 0 for empty
                else this.tiles[i][j] = new Tile(Integer.parseInt(splitRow[i][j]));
            }
        }
    }

    public Board(Board board, int numOfRows, int numOfCols) {
        this.numOfCols = numOfCols;
        this.numOfRows = numOfRows;
        this.tiles = new Tile[numOfRows][numOfCols];
        for (int i = 0; i < board.getTiles().length; i++) {
            for (int j = 0; j < board.getTiles()[i].length; j++) {
                tiles[i][j] = new Tile(board.getTiles()[i][j].getValue());
            }
        }
    }

    public void swapTiles(int i1, int j1, int i2, int j2) {
        Tile temp = this.tiles[i1][j1];
        this.tiles[i1][j1] = this.tiles[i2][j2];
        this.tiles[i2][j2] = temp;
    }

    public Tile[][] getTiles() {
        return this.tiles;
    }

    public int getNumOfRows() {
        return this.numOfRows;
    }

    public int getNumOfCols() {
        return this.numOfCols;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}

