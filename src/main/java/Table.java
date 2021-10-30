import Exceptions.WrongMoveException;

import java.io.File;
import java.util.*;

public class Table {
    private int[] table;
    private int rows;
    private int cols;

    // To można by było zastąpić operacjami % i / przy move(), ale tak jest czytelniej
    private int zeroIndex = -1;
    private int zeroRow = -1;
    private int zeroCol = -1;



    // Wygeneruj tablicę startową
    public Table(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        this.generatePuzzle();
    }

    // Pobierz tablicę startową z pliku
    public Table(String filename) {
        readPuzzleFromFile(filename);
    }

    // Konstruktor na potrzeby clone()
    private Table(int[] table, int rows, int cols, int zeroIndex, int zeroRow, int zeroCol) {
        this.table = table;
        this.rows = rows;
        this.cols = cols;

        this.zeroIndex = zeroIndex;
        this.zeroRow = zeroRow;
        this.zeroCol = zeroCol;
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
            if (this.zeroIndex == -1 && generated.get(i) == 0) {
                this.zeroIndex = i;
                this.zeroRow = i / this.rows;
                this.zeroCol = i % this.cols;
            }
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
                int value = sc.nextInt();
                this.table[index] = value;

                if (this.zeroIndex == -1 && value == 0) {
                    this.zeroIndex = index;
                    this.zeroRow = index / this.rows;
                    this.zeroCol = index % this.cols;
                }
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



    public Table move(Direction direction) throws WrongMoveException {
        // Nalezy wykonac kopie, aby zwrocic zmodyfikowany stan tablicy nienaruszając obecnego
        Table tmp = this.clone();

        if (direction == Direction.U) {
            if (this.zeroRow == 0) {
                throw new WrongMoveException("Cannot move up");
            } else {
                this.table[zeroIndex] = this.table[this.zeroIndex - this.cols];
                this.table[this.zeroIndex - this.cols] = 0;

                this.zeroIndex -= this.cols;
                this.zeroRow -= 1;
            }
        }

        if (direction == Direction.D) {
            if (this.zeroRow == this.rows - 1) {
                throw new WrongMoveException("Cannot move down");
            } else {
                this.table[zeroIndex] = this.table[this.zeroIndex + this.cols];
                this.table[this.zeroIndex + this.cols] = 0;

                this.zeroIndex += this.cols;
                this.zeroRow += 1;
            }
        }

        if (direction == Direction.L) {
            if (this.zeroCol == 0) {
                throw new WrongMoveException("Cannot move left");
            } else {
                this.table[zeroIndex] = this.table[this.zeroIndex - 1];
                this.table[this.zeroIndex - 1] = 0;

                this.zeroIndex -= 1;
                this.zeroCol -= 1;
            }
        }

        if (direction == Direction.R) {
            if (this.zeroCol == this.cols - 1) {
                throw new WrongMoveException("Cannot move right");
            } else {
                this.table[zeroIndex] = this.table[this.zeroIndex + 1];
                this.table[this.zeroIndex + 1] = 0;

                this.zeroIndex += 1;
                this.zeroCol += 1;
            }
        }

        // Przywróć poprzedni stan tablicy i zwróć zapisaną kopię
        Table clone = this.clone();
        this.table = tmp.table;
        this.zeroIndex = tmp.zeroIndex;
        this.zeroCol = tmp.zeroCol;
        this.zeroRow = tmp.zeroRow;

        return clone;
    }

    public Boolean checkIfSolved() {
        if (this.table[this.table.length - 1] != 0) {
            return false;
        }
        for (int i = 0; i < this.table.length - 1; i++) {
            if (this.table[i] != i + 1) {
                return false;
            }
        }
        return true;
    }




    public int[] getTable() {
        return table;
    }

    public int getZeroIndex() {
        return zeroIndex;
    }

    public int getZeroRow() {
        return zeroRow;
    }

    public int getZeroCol() {
        return zeroCol;
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

    public Table clone() {
        return new Table(this.table, this.rows, this.cols, this.zeroIndex, this.zeroRow, this.zeroCol);
    }
}
