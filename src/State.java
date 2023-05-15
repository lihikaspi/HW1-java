public class State {
    private Board board;

    public State(Board board) {
        this.board = board;
    }

    public boolean isGoal() {
        Tile[] tiles = board.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].getValue() != i+1) return false;
        }
        return true;
    }

    public Action[] actions() {
        int empty  = findEmptyTile();
        Action[] actions = new Action[0];
        if (empty-board.getRow() >= 0)
            actions = append(actions, new Action(board.getTiles()[empty], Direction.UP));
        else if (empty+board.getRow() < board.getTiles().length)
            actions = append(actions, new Action(board.getTiles()[empty], Direction.UP));
        else if (empty-1 >= 0)
            actions = append(actions, new Action(board.getTiles()[empty], Direction.UP));
        else if (empty+1 < board.getTiles().length)
            actions = append(actions, new Action(board.getTiles()[empty], Direction.UP));
        return actions;
    }

    private Action[] append(Action[] actions, Action add) {
        int len = board.getTiles().length+1;
        Action[] newActions = new Action[len];
        for (int i = 0; i < len-1; i++) {
            newActions[i] = actions[i];
        }
        newActions[len-1] = add;
        return newActions;
    }

    private int findEmptyTile() {
        for (int i = 0; i < board.getTiles().length; i++) {
            if (board.getTiles()[i].getValue() == 0) return i;
        }
        return -1;
    }

    public State result(Action action) {
        Board newBoard = new Board(this.board);
        action.moveTile(newBoard);
        return new State(newBoard);
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
