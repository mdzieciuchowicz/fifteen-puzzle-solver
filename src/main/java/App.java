import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("hello world");

        Table table = new Table("D:\\Pobrane\\folder_na_ten_gowno_program\\4x4_08_00442.txt");
        System.out.println(table.getTableAsString());

        Node root = new Node(null, table, 0, "");
        Solver bfs = new BFS(root);
        Node solvingNode = bfs.solve();
        System.out.println(solvingNode.getCurrentState().getMovesHistory());
        System.out.println(solvingNode.getCurrentState().getTreeDepth());
        System.out.println(((BFS) bfs).getNodeCounter());

    }
}
