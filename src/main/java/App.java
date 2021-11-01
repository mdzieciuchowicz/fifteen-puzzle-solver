import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {


        System.out.println("hello world");

        Table table = new Table("D:\\Pobrane\\sise\\4x4_08_00413.txt");
        System.out.println(table.getTableAsString());

        Node root = new Node(null, table, 0, "");

        Solver bfs = new BFS(root);
        Node solvingNode = bfs.solve();
        System.out.println("\n" + "DLA ALGORYTMU BFS:");
        Util.getInfoAboutResult(solvingNode, bfs);


        Solver dfs = new DFS(root);
        try {
            Node solvingNode2 = dfs.solve();
            System.out.println(solvingNode2);
            if (solvingNode2 != null) {
                System.out.println("\n" + "DLA ALGORYTMU DFS:");
                Util.getInfoAboutResult(solvingNode2, dfs);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Na badanym węźle: " + dfs.getNodesProcessed());
        }



    }
}
