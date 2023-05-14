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

    public Direction[] actions() { // ???????????

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
