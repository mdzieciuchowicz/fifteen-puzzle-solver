
public class Util {

    public static void getInfoAboutResult(Node node, Solver solver) {
        System.out.println("Rozwiązanie: " + node.getCurrentState().getMovesHistory());
        System.out.println("Głębokość węzła z rozwiązaniem: " + node.getCurrentState().getTreeDepth());
        System.out.println("Rozwiązana tablica: " + node.getCurrentState().getTable().getTableAsString());
        System.out.println("Rozwiązane: " + node.getCurrentState().getTable().checkIfSolved());
        System.out.println("Ilość przetworzonych węzłów: " + solver.getNodesProcessed());
        System.out.println("Ilość odwiedzonych węzłów: " + solver.getNodesVisited());
        System.out.println("Maksymalna odwiedzona głębokość drzewa: " + solver.getMaxDepthVisited());
        System.out.println("Czas trwania algorytmu: " + solver.getProcessingTime().toNanos() + "ns");
    }

}
