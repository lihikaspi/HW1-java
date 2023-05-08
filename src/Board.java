import java.util.Arrays;

public class Board {
    public Tile[] tiles;
    public int row; // size of each row
    public int col; // size of each column

    public Board(String boardString) {
        findSize(boardString); // find board size through string
        for (int i = 0; i < boardString.length(); i++) {
            if (boardString.charAt(i) == '|') tiles[i] = null; // next line
            else if (boardString.charAt(i) == '_') tiles[i] = new Tile(0); // 0 for empty tile
            else tiles[i] = new Tile(Integer.parseInt(String.valueOf(boardString.charAt(i)))); // char --> String --> int
        }
    }

    private void findSize(String boardString){
        int rowLen = 0;
        int colLen = 1;
        int i = 0;
        while(boardString.charAt(i) != '|') {
            rowLen++;
            i++;
        }
        for (i = 0; i < boardString.length(); i++) {
            if(boardString.charAt(i) == '|') colLen++;
        }
        this.row = rowLen;
        this.col = colLen;
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
