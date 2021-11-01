import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalTime;

public abstract class Solver {
    protected Node root;
    protected int nodesProcessed = 0;                     // Licznik węzłów przetworzonych - bez tych, które już wystąpiły
    protected int nodesVisited = 0;                       // Licznik wszystkich odwiedzonych węzłów
    protected int maxDepthVisited = 0;                    // Maksymalna osiągnięta głębokość rekursji
    protected LocalTime startTime;
    protected LocalTime finishTime;

    public Solver(Node root) {
        this.root = root;
    }

    public abstract Node solve();

    public int getNodesProcessed() {
        return this.nodesProcessed;
    }

    public int getNodesVisited() {
        return this.nodesVisited;
    }

    public int getMaxDepthVisited() {
        return maxDepthVisited;
    }

    public void setStartTime() {
        this.startTime = LocalTime.now();
    }

    public void setFinishTime() {
        this.finishTime = LocalTime.now();
    }

    public Duration getProcessingTime() {
        return Duration.between(startTime, finishTime);
    }

    public void updateDepthVisited(Node node) {
        int currentDepth = node.getCurrentState().getTreeDepth();
        if (currentDepth > this.maxDepthVisited) {
            this.maxDepthVisited = currentDepth;
        }
    }
}
