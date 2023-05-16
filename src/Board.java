import java.util.Arrays;

public class Board {
    private Tile[][] tiles;
    private final int numOfRows; // how many rows
    private final int numOfCols; // how many columns

    /**
     * regular constructor
     * @param boardString String of board
     */
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

    /**
     * copy constructor
     * @param board board to copy
     * @param numOfRows how many rows
     * @param numOfCols how many columns
     */
    public Board(Board board, int numOfRows, int numOfCols) {
        this.numOfCols = numOfCols;
        this.numOfRows = numOfRows;
        this.tiles = new Tile[numOfRows][numOfCols];
        for (int i = 0; i < board.getTiles().length; i++) { // copy the values of the array -- not addresses
            for (int j = 0; j < board.getTiles()[i].length; j++) {
                tiles[i][j] = new Tile(board.getTiles()[i][j].getValue());
            }
        }
    }

    /**
     * swapping two tiles on the board
     * @param i1 row of fist tile
     * @param j1 col of first tile
     * @param i2 row of second tile
     * @param j2 col of second tile
     */
    public void swapTiles(int i1, int j1, int i2, int j2) {
        Tile temp = this.tiles[i1][j1];
        this.tiles[i1][j1] = this.tiles[i2][j2];
        this.tiles[i2][j2] = temp;
    }

    /**
     * checks if the board is complete
     * @return is target
     */
    public boolean checkTarget() {
        int count = 1;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (i == tiles.length-1 && j == tiles[i].length-1) return true;
                if (tiles[i][j].getValue() != count) return false;
                else count++;
            }
        }
        return true;
    }

    /**
     * get tiles array
     * @return tiles array
     */
    public Tile[][] getTiles() {
        return this.tiles;
    }

    /**
     * get how many rows
     * @return number of rows
     */
    public int getNumOfRows() {
        return this.numOfRows;
    }

    /**
     * get how many columns
     * @return number of columns
     */
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

