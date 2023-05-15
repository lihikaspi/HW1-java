public class State {
    private final Board board;

    public State(Board board) {
        this.board = board;
    }

    public boolean isGoal() {
        int count = 1;
        Tile[][] tiles = board.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (i == tiles.length-1 && j == tiles[i].length-1) return true;
                if (tiles[i][j].getValue() != count) return false;
                else count++;
            }
        }
        return true;
    }

    public Action[] actions() {
        int[] empty  = findEmptyTile();
        Action[] actions = new Action[0];
        if (empty[0]+1 < board.getNumOfRows())
            actions = append(actions, new Action(board.getTiles()[empty[0]+1][empty[1]], Direction.UP));
        if (empty[0]-1 >= 0)
            actions = append(actions, new Action(board.getTiles()[empty[0]-1][empty[1]], Direction.DOWN));
        if (empty[1]-1 >= 0)
            actions = append(actions, new Action(board.getTiles()[empty[0]][empty[1]-1], Direction.RIGHT));
        if (empty[1]+1 < board.getNumOfCols())
            actions = append(actions, new Action(board.getTiles()[empty[0]][empty[1]+1], Direction.LEFT));
        return actions;
    }

    private Action[] append(Action[] actions, Action add) {
        Action[] newActions = new Action[actions.length+1];
        for (int i = 0; i < actions.length; i++) {
            newActions[i] = actions[i];
        }
        newActions[actions.length] = add;
        return newActions;
    }

    private int[] findEmptyTile() {
        int[] empty = new int[2];
        for (int i = 0; i < board.getTiles().length; i++) {
            for (int j = 0; j < board.getTiles()[i].length; j++) {
                if (board.getTiles()[i][j].getValue() == 0) {
                    empty[0] = i;
                    empty[1] = j;
                    return empty;
                }
            }
        }
        return empty;
    }

    public State result(Action action) {
        Board newBoard = new Board(this.board, this.board.getNumOfRows(), this.board.getNumOfCols());
        action.moveTile(newBoard);
        return new State(newBoard);
    }

    public Board getBoard(){
        return this.board;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
