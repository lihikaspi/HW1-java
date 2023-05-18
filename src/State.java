public class State {
    private final Board board;

    public State(Board board) {
        this.board = board;
    }

    public boolean isGoal() {
        return this.board.checkTarget();
    }

    public Action[] actions() {
        int[] empty  = board.findTile(0);
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

    /**
     * Append a new action to an array
     * @param actions Array of existing actions
     * @param add Action to be added to array
     * @return New array containing all actions
     */
    private Action[] append(Action[] actions, Action add) {
        Action[] newActions = new Action[actions.length+1];
        for (int i = 0; i < actions.length; i++) {
            newActions[i] = actions[i];
        }
        newActions[actions.length] = add;
        return newActions;
    }

    public State result(Action action) {
        Board newBoard = new Board(this.board);
        newBoard.moveTile(action);
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
