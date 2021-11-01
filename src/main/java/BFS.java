import java.util.ArrayList;
import java.util.List;

public class BFS extends Solver{
    private List<Node> queue = new ArrayList<>();       // FIFO
    private List<Node> explored = new ArrayList<>();    // Lista zbadanych już nodów - zapobiega nieskończonej pętli

    public BFS(Node root) {
        super(root);
        this.queue.add(root);
    }

    // Plan jest taki: leci sobie rekurencja
    // jak znajdzie rozwiązanie - zwraca node, który ma state ze wszystkimi detalami
    // jak nie znajdzie - zwraca nulla i idzie dalej
    public Node solve() {

        // Ustaw moment początku przetwarzania
        if (this.nodesProcessed == 0) {
            this.setStartTime();
        }

        // Przejdź po kolejce i sprawdź, czy istnieje rozwiązanie
        // Kopia jest potrzebna, aby nie wystapił CurrentModificationException podczas iteracji po liście - nie można
        // dodawać elementów do listy po której aktualnie się iteruje
        List<Node> copyOfQueue = new ArrayList<>(this.queue);

        if (this.queue.isEmpty()) {
            return null;
        }

        for (Node nodeFromQueue : copyOfQueue) {

            // Rozpocznij analizę węzła - usuń go z kolejki
            this.queue.remove(nodeFromQueue);

            // Aktualizacja statystyk
            this.nodesVisited++;
            this.updateDepthVisited(nodeFromQueue);

            if (nodeFromQueue.getCurrentState().getTable().checkIfSolved()) {
                this.setFinishTime();
                return nodeFromQueue;
            }

            // Jeśli był już badany wcześniej - idź dalej
            if (this.explored.contains(nodeFromQueue)) {
                continue;
            }

//            System.out.println("Badany węzeł przy BFS:" + nodeFromQueue.getCurrentState().getTable().getTableAsString());

            // Jeśli nie był jeszcze badany - oznacz jako zbadany i przejdz do badania
            this.explored.add(nodeFromQueue);
            this.nodesProcessed++;

            // Dla węzła utwórz dzieci robiąc ruch w każdą stronę
            for (Direction direction : Direction.values()) {
                Node child = nodeFromQueue.move(direction);
                // Aby nie dawało child null gdy nie można ruszyć
                if (child != null) {
                    this.queue.add(nodeFromQueue.move(direction));
                }
            }
        }

        return solve();
    }
}
