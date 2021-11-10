import java.util.ArrayList;
import java.util.List;

public class DFS extends Solver {
    private List<Node> explored = new ArrayList<>();    // Lista zbadanych już nodów - zapobiega nieskończonej pętli

    public DFS(Node root, String parameter) {
        super(root, parameter);
    }

    public Node solve() {
        this.setStartTime();
        return solveDFS(this.root);
    }

    private Node solveDFS(Node node) {
        // Aktualizacja statystyk
        this.nodesVisited ++;
        this.updateDepthVisited(node);

        // Jeśli znaleziono rozwiązanie
        if (node.getCurrentState().getTable().checkIfSolved()) {
            this.setFinishTime();
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
        this.nodesProcessed++;

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
}
