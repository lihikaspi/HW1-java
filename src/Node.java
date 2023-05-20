public class Node {
    private final State state;
    private final Action action;
    private final Node parent;

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
        return state.getBoard().calcHeuristicValue();
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
