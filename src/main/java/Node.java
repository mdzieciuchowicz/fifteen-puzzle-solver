import Exceptions.WrongMoveException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Node> children = new ArrayList<>();
    private final Node parent;
    private final State currentState;

    public Node(Table table) {
        this.parent = null;
        this.currentState = new State(table, 0, "");
    }

    private Node(Node parent, Table table, int treeDepth, String movesHistory) {
        this.parent = parent;
        this.currentState = new State(table, treeDepth, movesHistory);
    }

    public void appendChild(Node child) {
        if (child == null) {
            return;
        }
        this.children.add(child);
    }

    public Node move(Direction direction) {
        try {
            // Zamien wartosci w tablicy, dodaj ruch do historii a na końcu dodaj jako dziecko
            Table afterMove = this.getCurrentState().getTable().move(direction);
            String newHistory = this.getCurrentState().getMovesHistory() + direction.toString();

            Node child = new Node(this, afterMove, this.getCurrentState().getTreeDepth() + 1, newHistory);
            this.children.add(child);
            return child;

        } catch (WrongMoveException e) {
//            System.out.println(e.toString());
//            System.out.println("Dla tablicy o stanie: " + this.getCurrentState().getTable().getTableAsString());
            return null;
        }
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


    // To będzie wykorzystywane do porównywania nodów - czy node o innym adresie w pamięci nie wystąpił już wcześniej w algorytmie -
    // - to znaczy czy nie ma takiej samej kolejności w Table
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
