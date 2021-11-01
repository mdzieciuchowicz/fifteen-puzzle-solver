import java.util.ArrayList;
import java.util.List;

public class DFS implements Solver {
    private List<Node> explored = new ArrayList<>();    // Lista zbadanych już nodów - zapobiega nieskończonej pętli
    private Node root;
    private int nodeCounter = 0;

    public DFS(Node root) {
        this.root = root;
    }

    public Node solve() {
        return solveDFS(this.root);
    }

    private Node solveDFS(Node node) {
        // Jeśli znaleziono rozwiązanie
        if (node.getCurrentState().getTable().checkIfSolved()) {
            return node;
        }

        // Sprawdź czy wystąpiło
        // TODO: zostawić explored czy nie?
//        if (this.explored.contains(node)) {
//            return null;
//        }

        // Node jeszcze nie wystąpił
        // Ograniczenie głębokości szukania drzewa
        if (node.getCurrentState().getTreeDepth() >= 20) {
            return null;
        }

        this.explored.add(node);
        this.nodeCounter ++;

        // sprawdź rekursyjnie dzieci
        for (Direction direction : Direction.values()) {
            Node child = node.move(direction);
            if (child != null) {
                Node res = solveDFS(child);
                if (res != null) {
                    return res;
                }
            }
        }

        // w żadnym węźle nie znaleziono rozwiązania
        return null;
    }

    public int getNodeCounter() {
        return nodeCounter;
    }
}
