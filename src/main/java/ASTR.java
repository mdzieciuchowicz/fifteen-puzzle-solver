import java.util.ArrayList;
import java.util.List;

public class ASTR extends Solver {
    private List<Node> open = new ArrayList<>();
    private List<Node> closed = new ArrayList<>();
    private Node optimalNode;
    private double bestNodeValue = Integer.MAX_VALUE;


    public ASTR(Node root, String parameter) {
        super(root, parameter);
        root.calcAndSetNodeValue(this.metric.calc(root));
        this.open.add(root);
    }

    @Override
    public Node solve() {
        if (this.nodesProcessed == 0) {
            this.setStartTime();
        }

        List<Node> copyOfQueue = new ArrayList<>(this.open);
        for (Node nodeFromQueue : copyOfQueue) {
            this.nodesVisited++;
            this.updateDepthVisited(nodeFromQueue);

            if (nodeFromQueue.getCurrentState().getTable().checkIfSolved()) {
                this.setFinishTime();
                return nodeFromQueue;
            }

            if(nodeFromQueue.getNodeValue() <= this.bestNodeValue) {
                this.optimalNode = nodeFromQueue;
                this.bestNodeValue = nodeFromQueue.getNodeValue();
            }
        }

        //węzeł optymalny który bedzie miał zaraz dzieci dodajemy do closed
        closed.add(optimalNode);
        open.remove(optimalNode);
        this.nodesProcessed++;

        for (Direction direction : Direction.values()) {
            Node child = optimalNode.move(direction);
            if (closed.contains(child)) continue;
            //nie dodajemy dziecka do open jeśli już tam jest i nie liczymy drugi raz metryki
            else if (child != null && !open.contains(child)) {
                this.open.add(child);
                child.calcAndSetNodeValue(this.metric.calc(child));
            }
        }

        this.bestNodeValue = open.get(0).getNodeValue(); //ustawiamy na pierwszy element kolejki
        return solve();
    }
}

