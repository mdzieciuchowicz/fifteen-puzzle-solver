import Exceptions.WrongMoveException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final List<Node> children = new ArrayList<>();
    private final Node parent;
    private int nodeValue;
    private final Table table;
    private final int treeDepth;
    private final String movesHistory;

    public Node(Table table) {
        this.parent = null;
        this.table = table;
        this.treeDepth = 0;
        this.movesHistory = "";
    }

    private Node(Node parent, Table table, int treeDepth, String movesHistory) {
        this.parent = parent;
        this.table = table;
        this.treeDepth = treeDepth;
        this.movesHistory = movesHistory;
    }

    public Node move(Direction direction) {
        try {
            // Zamien wartosci w tablicy, dodaj ruch do historii a na końcu dodaj jako dziecko
            Table afterMove = this.getTable().move(direction);
            String newHistory = this.getMovesHistory() + direction.toString();

            Node child = new Node(this, afterMove, this.getTreeDepth() + 1, newHistory);
            this.children.add(child);
            return child;

        } catch (WrongMoveException e) {
            return null;
        }
    }

    public List<Node> getChildren() {
        return children;
    }

    public Node getParent() {
        return parent;
    }

    public int getNodeValue() {
        return nodeValue;
    }

    public void calcAndSetNodeValue(int nodeValue) {
        this.nodeValue = nodeValue + this.getTreeDepth();
    }

    public Table getTable() {
        return table;
    }

    public int getTreeDepth() {
        return treeDepth;
    }

    public String getMovesHistory() {
        return movesHistory;
    }

    // To będzie wykorzystywane do porównywania nodów - czy node o innym adresie w pamięci nie wystąpił już wcześniej w algorytmie -
    // - to znaczy czy nie ma takiej samej kolejności w Table
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return new EqualsBuilder()
                .append(this.getTable(), node.getTable())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.getTable())
                .toHashCode();
    }
}
