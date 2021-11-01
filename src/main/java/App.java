import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("hello world");

        Table table = new Table("D:\\Pobrane\\sise\\4x4_08_00413.txt");
        System.out.println(table.getTableAsString());

        Node root = new Node(null, table, 0, "");

//        Solver bfs = new BFS(root);
//        Node solvingNode = bfs.solve();
//        System.out.println(solvingNode.getCurrentState().getMovesHistory());
//        System.out.println(solvingNode.getCurrentState().getTreeDepth());
//        System.out.println(((BFS) bfs).getNodeCounter());

        Solver dfs = new DFS(root);
        Node solvingNode2 = dfs.solve();
        System.out.println(solvingNode2);
        if (solvingNode2 != null) {
            System.out.println(solvingNode2.getCurrentState().getMovesHistory());
            System.out.println(solvingNode2.getCurrentState().getTreeDepth());
            System.out.println(solvingNode2.getCurrentState().getTable().getTableAsString());
            System.out.println(solvingNode2.getCurrentState().getTable().checkIfSolved());
            System.out.println(((DFS) dfs).getNodeCounter());
        }


    }
}
