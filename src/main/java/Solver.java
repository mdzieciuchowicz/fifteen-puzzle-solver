import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Solver {
    protected Node root;
    protected int nodesProcessed = 0;                               // Licznik węzłów przetworzonych - bez tych, które już wystąpiły
    protected int nodesVisited = 0;                                 // Licznik wszystkich odwiedzonych węzłów
    protected int maxDepthVisited = 0;                              // Maksymalna osiągnięta głębokość rekursji
    protected LocalTime startTime;
    protected LocalTime finishTime;
    protected List<Direction> directionOrder = new ArrayList<>();   // kolejność analizowania ruchów
    protected Metric metric = null;

    public Solver(Node root, String parameter) {
        this.root = root;

        switch (parameter) {
            case "hamm": {
                this.metric = new Hamming();
                break;
            }
            case "manh": {
                this.metric = new Manhattan();
                break;
            }
            default: {
                if (parameter.matches("(?=.*L)(?=.*R)(?=.*D)(?=.*U).{4}")) {
                    // Dla algorytmów BFS i DFS
                    String[] directions = parameter.split("");
                    for (int i = 0; i < 4; i++) {
                        this.directionOrder.add(Direction.valueOf(directions[i]));
                    }
                } else {
                    // Gdy nie pasuje do żadnego algorytmu
                    throw new IllegalArgumentException("Podano zły parametr dla strategii");
                }
            }
        }
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

    // @param solvingNode - węzeł uzyskany w wyniku metody solve()
    // @param resultFilename - nazwa pliku do którego ma zostać zapisane rozwiązanie
    // @param dataFilename - nazwa pliku do którego mają zostać dodatkowe informacje o procesie obliczeniowym
    public void saveResultsToFile(Node solvingNode, String resultFilename, String dataFilename) {
        try {
            PrintWriter resultWriter = new PrintWriter(resultFilename, "UTF-8");
            PrintWriter dataWriter = new PrintWriter(dataFilename, "UTF-8");

            if (solvingNode == null) {
                resultWriter.println("-1");
                dataWriter.println("-1");
            } else {
                // Zapis rozwiązania
                resultWriter.println(solvingNode.getCurrentState().getTreeDepth());
                resultWriter.println(solvingNode.getCurrentState().getMovesHistory());

                // Zapis dodatkowych danych
                dataWriter.println(solvingNode.getCurrentState().getTreeDepth());
                dataWriter.println(this.getNodesVisited());
                dataWriter.println(this.getNodesProcessed());
                dataWriter.println(this.getMaxDepthVisited());

                // TODO: (liczba rzeczywista z dokładnością do 3 miejsc po przecinku): czas trwania procesu obliczeniowego w milisekundach.
                dataWriter.println(
                        String.format("%.3f", (this.getProcessingTime().toNanos() / 1000000f))
                );
                dataWriter.println(
                        this.getProcessingTime().toMillis()
                );
                dataWriter.println(
                        String.format("%.3f", (float)this.getProcessingTime().toMillis())
                );
                dataWriter.println(
                        String.format("%.3f", (float)this.getProcessingTime().toNanos())
                );
            }

            resultWriter.close();
            dataWriter.close();

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
