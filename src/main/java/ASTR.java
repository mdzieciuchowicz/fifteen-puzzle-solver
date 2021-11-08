import java.util.ArrayList;
import java.util.List;

public class ASTR extends Solver {
    private List<Node> queue = new ArrayList<>();
    private List<Node> explored = new ArrayList<>();
    private Node optimalNode;
    private Metric metric;
    private double bestMetricValue = 10000; //duza wartosc na razie do porownywania

        public ASTR(Node root, Metric metric) {
            super(root);
            this.metric = metric;
            this.queue.add(root);
        }

        @Override
        public Node solve() {
            // Ustaw moment początku przetwarzania
            if (this.nodesProcessed == 0) {
//                System.out.println("zaczynam");
                this.setStartTime();
            }

            List<Node> copyOfQueue = new ArrayList<>(this.queue);

            for (Node nodeFromQueue : copyOfQueue) {
                // Jeśli był już badany wcześniej - idź dalej

                this.nodesVisited++;
                this.updateDepthVisited(nodeFromQueue);
//                System.out.println("głębokość:" + nodeFromQueue.getCurrentState().getTreeDepth());

                if (this.explored.contains(nodeFromQueue)) {
                    continue;
                }

                double currentMetricValue = this.metric.calc(nodeFromQueue);

//                System.out.println("metryka najlepsza: " + this.bestMetricValue);
//                System.out.println("metryka aktualna: " + currentMetricValue);
//                System.out.println(queue.toString());

                if (nodeFromQueue.getCurrentState().getTable().checkIfSolved()) {
                    this.setFinishTime();
//                    System.out.println("kończe");
                    return nodeFromQueue;
                }

                if(currentMetricValue <= this.bestMetricValue) {
                    this.optimalNode = nodeFromQueue;
                    this.bestMetricValue = currentMetricValue;
                }

                this.queue.remove(nodeFromQueue);

                this.nodesProcessed++;
            }
            if(this.queue.isEmpty()) {
//                System.out.println("siema");
                if (!this.explored.contains(optimalNode)) {
                    this.explored.add(optimalNode);
                    // Dla węzła utwórz dzieci robiąc ruch w każdą stronę
                    for (Direction direction : Direction.values()) {
                        Node child = optimalNode.move(direction);
                        // Aby nie dawało child null gdy nie można ruszyć
                        if (child != null && !explored.contains(child)) {
                            this.queue.add(optimalNode.move(direction));
                        }
                    }
                }
                else {
//                    System.out.println("jestem tu");
//                    System.out.println(this.optimalNode);
                    this.explored.remove(optimalNode.getParent());
                    this.optimalNode = optimalNode.getParent();
                    this.bestMetricValue = this.metric.calc(optimalNode);
//                    System.out.println(this.optimalNode);
                }
            }
            return solve();
        }
}

