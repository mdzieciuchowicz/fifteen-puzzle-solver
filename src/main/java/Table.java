import java.io.File;
import java.util.*;

public class Table {
    private int[] table;
    private int rows;
    private int cols;

    public Table(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        this.generatePuzzle();
    }

    public Table(String filename) {
        readPuzzleFromFile(filename);
    }

    public void generatePuzzle() {
        int max = this.rows * this.cols - 1;
        List<Integer> generated = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            generated.add(i);
        }
        Collections.shuffle(generated);

        this.table = new int[rows * cols];
        for (int i = 0; i <= max; i++) {
            this.table[i] = generated.get(i);
        }
    }

    public void readPuzzleFromFile(String filename){
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);

            this.cols = sc.nextInt();
            this.rows = sc.nextInt();

            int index = 0;
            this.table = new int[this.cols * this.rows];

            while (sc.hasNextInt()) {
                this.table[index] = sc.nextInt();
                index ++;
            }

            // Validate if every number in range 0 - max occurred once
            int[] tableCopy = Arrays.copyOf(this.table, this.table.length);
            Arrays.sort(tableCopy);

            for (int i = 0; i < tableCopy.length; i++) {
                if (tableCopy[i] != i) {
                    throw new Exception("Invalid numbers in file");
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public String getTableAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i : this.table) {
            sb.append(i);
            sb.append(", ");
        }

        return sb.toString();
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
