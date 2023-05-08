public class State {

    public boolean isGoal() {

    }

    public int[] actions() { // ???????????

    }

    public State result(Action action) {

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
