public class Node {
    private final State state;
    private final Action action;
    private final Node parent;

    /**
     * regular constructor
     * @param state current state
     * @param action action to perform
     * @param parent previous Node
     */
    public Node(State state, Action action, Node parent) {
        this.state = state;
        this.action = action;
        this.parent = parent;
    }

    /**
     * start Node constructor
     * @param state current state
     */
    public Node(State state) {
        this.state = state;
        this.action = null;
        this.parent = null;
    }

    /**
     * expand the current Node to each action available
     * @return array of successor Nodes
     */
    public Node[] expand() {
        Action[] actions = state.actions();
        Node[] expand = new Node[actions.length];
        for (int i = 0; i < actions.length; i++) {
            expand[i] = new Node(this.state.result(actions[i]), actions[i], this);
        }
        return expand;
    }

    /**
     * find heuristic value of current board
     * @return heuristic value
     */
    public int heuristicValue() {
        return state.getBoard().calcHeuristicValue();
    }

    /**
     * get current State
     * @return current State
     */
    public State getState() {
        return this.state;
    }

    /**
     * get Action
     * @return Action
     */
    public Action getAction() {
        return this.action;
    }

    /**
     * get previous Node
     * @return previous Node
     */
    public Node getParent() {
        return this.parent;
    }
}
