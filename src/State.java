public class State {
    private final Board board;

    /**
     * regular constructor
     * @param board current board
     */
    public State(Board board) {
        this.board = board;
    }

    /**
     * checks if the all tiles are in their place
     * @return if the board is completed
     */
    public boolean isGoal() {
        return this.board.checkTarget();
    }

    /**
     * checks what are the available actions on current board
     * @return array of available actions
     */
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

    /**
     * move a tile and create new State
     * @param action action to perform
     * @return new State after move
     */
    public State result(Action action) {
        Board newBoard = new Board(this.board);
        newBoard.moveTile(action);
        return new State(newBoard);
    }

    /**
     * get current board
     * @return current board
     */
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
