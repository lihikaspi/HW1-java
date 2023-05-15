import java.util.Arrays;

public class Board {
    private Tile[] tiles;
    private int row; // size of each row
    private int col; // size of each column

    public Board(String boardString) {
        findSize(boardString); // find board size through string
        for (int i = 0; i < boardString.length(); i++) {
            if (boardString.charAt(i) == '|') continue;
            else if (boardString.charAt(i) == '_') tiles[i] = new Tile(0); // 0 for empty tile
            else tiles[i] = new Tile(Integer.parseInt(String.valueOf(boardString.charAt(i)))); // char --> String --> int
        }
    }

    public Board(Board board) {
        this.tiles = new Tile[board.getTiles().length];
        for (int i = 0; i < board.getTiles().length; i++) {
            tiles[i] = new Tile(board.getTiles()[i].getValue());
        }
    }

    public Board(Tile[] tiles) {
        this.tiles = new Tile[tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile(tiles[i].getValue());
        }
    }

    private void findSize(String boardString){
        int rowLen = 0;
        int colLen = 1;
        int i = 0;
        for (i = 0; i < boardString.length(); i++) {
            if(boardString.charAt(i) == '|') colLen++;
            if (boardString.charAt(i) != '|') rowLen++;
        }
        this.row = rowLen;
        this.col = colLen;
    }

    public Tile[] getTiles(){
        return tiles;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol(){
        return this.col;
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
