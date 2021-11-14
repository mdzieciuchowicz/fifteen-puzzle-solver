import java.io.FileNotFoundException;

public class App {

    /*      Program wykonali:
     *       Michał Dzieciuchowicz 229874
     *       Jakub Czarnecki       229858
     *
     *       Argumenty programu
     *       1. typ Solvera - bfs / dfs / astr
     *       2. kolejność sprawdzania ruchów - np. LUDR     //  heurystyka manh/hamm
     *       3. ścieżka pliku wejściowego
     *       4. ścieżka pliku wyjściowego
     *       5. ścieżka pliku do zapisu danych dodatkowych
     */

    public static void main(String[] args) {
        Solver solver = null;

        try {
            if ( args.length != 5 ) {
                throw new IllegalArgumentException("Liczba argumentów musi wynosić 5");
            }

            // Pobranie danych wejściowych
            Table table = new Table(args[2]);
            Node rootNode = new Node(table);

            // typ Solvera
            switch (args[0]) {
                case "bfs":     solver = new BFS(rootNode, args[1]); break;
                case "dfs":     solver = new DFS(rootNode, args[1]); break;
                case "astr":    solver = new ASTR(rootNode, args[1]); break;
                default:        throw new IllegalArgumentException("Podano nieprawidłowy typ algorytmu rozwiązującego");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Nieprawidłowa składnia argumentów: " + e.toString());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono podanego pliku z danymi wejściowymi");
            System.exit(1);
        }

        // Użytkownik poprawnie podał parametry i został utworzony solver
        Node solvingNode = solver.solve();
        // Zapis rozwiązania do pliku
        solver.saveResultsToFile(solvingNode, args[3], args[4]);

    }
}
