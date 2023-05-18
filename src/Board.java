import java.util.Arrays;

public class Board {
    private Tile[][] tiles;
    private final int numOfRows; // how many rows
    private final int numOfCols; // how many columns

    /**
     * Regular constructor
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
     * Copy constructor
     * @param board Board to copy
     */
    public Board(Board board) {
        this.numOfCols = board.getNumOfCols();
        this.numOfRows = board.getNumOfRows();
        this.tiles = new Tile[numOfRows][numOfCols];
        for (int i = 0; i < board.getTiles().length; i++) { // copy the values of the array -- not addresses
            for (int j = 0; j < board.getTiles()[i].length; j++) {
                tiles[i][j] = new Tile(board.getTiles()[i][j].getValue());
            }
        }
    }

    /**
     * Swaps two tiles on the board
     * @param i1 Row of fist tile
     * @param j1 Col of first tile
     * @param i2 Row of second tile
     * @param j2 Col of second tile
     */
    public void swapTiles(int i1, int j1, int i2, int j2) {
        Tile temp = this.tiles[i1][j1];
        this.tiles[i1][j1] = this.tiles[i2][j2];
        this.tiles[i2][j2] = temp;
    }

    /**
     * Checks if the board is completed
     * @return Is target board
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
     * Finds the place of the tile with a given value
     * @param value Value of tile to find
     * @return Array containing coordinates of found tile
     */
    public int[] findTile(int value) {
        int[] place = new int[2];
        for (int i = 0; i < this.numOfRows; i++) {
            for (int j = 0; j < this.numOfCols; j++) {
                if (this.tiles[i][j].getValue() == value) {
                    place[0] = i;
                    place[1] = j;
                    return place;
                }
            }
        }
        return place;
    }

    /**
     * Calculates the current board's heuristic value
     * @return Integer representing the heuristic value
     */
    public int calcHeuristicValue() {
        int count = 1;
        int numOfReversals = 0;
        int distanceSum = 0;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                if (reversal(i, j, count)) numOfReversals++;
                int[] next = findTile(count);
                distanceSum += (absolute(i-next[0]) + absolute(j-next[1]));
                count++;
            }
        }
        return fullRowOrCol() + distanceSum + numOfReversals*2;
    }

    /**
     * checks how many complete outer row/col
     * @return number of not complete outer row/col
     */
    public int fullRowOrCol() {
        int full = 0;
        int count = 0;

        for (int i = 0; i < numOfRows; i++) {
            if (tiles[i][0].getValue() != (numOfCols*i)+1) count++;
        }
        if (count != 0) full += numOfRows;

        count = 0;
        for (int i = 0; i < numOfRows; i++) {
            if (tiles[i][numOfCols-1].getValue() != (numOfCols*(i+1))) count++;
        }
        if (count != 0) full += numOfRows;

        count = 0;
        for (int j = 0; j < numOfCols; j++) {
            if (tiles[0][j].getValue() != (j+1)) count++;
        }
        if (count != 0) full += numOfCols;

        count = 0;
        for (int j = 0; j < numOfCols-1; j++) {
            if (tiles[numOfRows-1][j].getValue() != (numOfCols*(numOfRows-1))+(j+1)) count++;
        }
        if (tiles[numOfRows-1][numOfCols-1].getValue() != 0) count++;
        if (count != 0) full += numOfCols;
        count = 0;

        return full;
    }

    public int snakes() {
        //TODO: search for length of snakes on board, positive weight for snakes
    }

    /**
     * Checks if tile should be in a swapped position with a tile next to it
     * @param i Row of tile
     * @param j Column of tile
     * @param value Value that should be in the tile's place
     * @return Does the tile have a direct reversal around it
     */
    public boolean reversal(int i, int j, int value) {
        int valueRe = tiles[i][j].getValue();
        if (i+1 < numOfRows && valueRe == (i+2)*(j+1) && tiles[i+1][j].getValue() == value) return true;
        if (i-1 >= 0 && valueRe == i*(j+1) && tiles[i-1][j].getValue() == value) return true;
        if (j+1 < numOfCols && valueRe == (i+1)*(j+2) && tiles[i][j+1].getValue() == value) return true;
        if (j-1 >= 0 && valueRe == (i+1)*j && tiles[i][j-1].getValue() == value) return true;
        return false;
    }

    /**
     * Moves a tile into the empty spot
     * @param action Action to make, containing a tile and a direction
     */
    public void moveTile(Action action) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].getValue() == 0) { //Check where the empty tile is first
                    switch (action.getDirection()) {
                        case RIGHT:
                            swapTiles(i, j, i, j-1);
                            return;

                        case LEFT:
                            swapTiles(i, j, i, j+1);
                            return;

                        case UP:
                            swapTiles(i, j, i+1, j);
                            return;

                        case DOWN:
                            swapTiles(i, j, i-1, j);
                            return;
                    }
                }
            }
        }
    }

    /**
     * Calculates absolute value of int
     * @param num Number
     * @return Absolute value
     */
    private int absolute(int num) {
        if (num >= 0) return num;
        return -num;
    }

    /**
     * Get tiles array
     * @return Tiles array
     */
    public Tile[][] getTiles() {
        return this.tiles;
    }

    /**
     * Get how many rows
     * @return Number of rows
     */
    public int getNumOfRows() {
        return this.numOfRows;
    }

    /**
     * Get how many columns
     * @return Number of columns
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

