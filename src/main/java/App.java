import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {


        System.out.println("hello world");

        Table table = new Table("C:\\Users\\kczar\\Downloads\\15\\4x4_13_00001.txt");
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

        Solver astr = new ASTR(root, new Hamming());
        try {
            Node solvingNode3 = astr.solve();
            System.out.println(solvingNode3);
            if (solvingNode3 != null) {
                System.out.println("\n" + "DLA ALGORYTMU ASTR:");
                Util.getInfoAboutResult(solvingNode3, astr);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Na badanym węźle: " + astr.getNodesProcessed());
            System.out.println("Na glebokosci : " + astr.getMaxDepthVisited());
        }



    }
}
