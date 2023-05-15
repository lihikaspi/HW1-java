public class Node {
    private State state;
    private Action action;
    private Node parent;

    public Node(State state, Action action, Node parent) {
        this.state = state;
        this.action = action;
        this.parent = parent;
    }

    public Node(State state) {
        this.state = state;
        this.action = null;
        this.parent = null;
    }

    public Node[] expand() {
        Action[] actions = state.actions();
        Node[] expand = new Node[actions.length];
        for (int i = 0; i < actions.length; i++) {
            expand[i] = new Node(this.state.result(actions[i]), actions[i], this);
        }
        return expand;
    }

    public int heuristicValue() {
        int value = state.getBoard().getCol() * state.getBoard().getRow();
        for (int i = 0; i < state.getBoard().getTiles().length; i++) {
            if (state.getBoard().getTiles()[i].getValue() == i+1) value--;
            else return value;
        }
        return value;
    }

    public State getState() {
        return this.state;
    }

    public Action getAction() {
        return this.action;
    }

    public Node getParent() {
        return this.parent;
    }
}
