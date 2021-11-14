import Exceptions.WrongMoveException;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final List<Node> children = new ArrayList<>();
    private int nodeValue;
    private final Table table;
    private final int treeDepth;
    private final String movesHistory;

    public Node(Table table) {
        this.table = table;
        this.treeDepth = 0;
        this.movesHistory = "";
    }

    private Node(Table table, int treeDepth, String movesHistory) {
        this.table = table;
        this.treeDepth = treeDepth;
        this.movesHistory = movesHistory;
    }

    public Node move(Direction direction) {
        try {
            // Zamien wartosci w tablicy, dodaj ruch do historii a na końcu dodaj jako dziecko
            Table afterMove = this.getTable().move(direction);
            String newHistory = this.getMovesHistory() + direction.toString();

            Node child = new Node(afterMove, this.getTreeDepth() + 1, newHistory);
            this.children.add(child);
            return child;

        } catch (WrongMoveException e) {
            return null;
        }
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
}
