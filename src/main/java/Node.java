import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class Node {
    private List<Node> children;
    private Node parent;
    private State currentState;
    private String movesHistory;

    public Node(Node parent, String movesHistory) {
        this.parent = parent;
        this.movesHistory = movesHistory;
    }

    public void appendChild(Node child) {
        this.children.add(child);
    }

    public List<Node> getChildren() {
        return children;
    }

    public Node getParent() {
        return parent;
    }

    public State getCurrentState() {
        return currentState;
    }

    public String getMovesHistory() {
        return movesHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return new EqualsBuilder()
                .append(currentState.getTable(), node.currentState.getTable())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(currentState.getTable())
                .toHashCode();
    }
}
