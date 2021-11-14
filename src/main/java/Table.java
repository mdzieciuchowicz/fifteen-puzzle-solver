import Exceptions.WrongMoveException;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Table {
    private int[] table;
    private int rows;
    private int cols;

    // To można by było zastąpić operacjami % i / przy move(), ale tak jest czytelniej
    private int zeroIndex = -1;
    private int zeroRow = -1;
    private int zeroCol = -1;


    // Pobierz tablicę startową z pliku
    public Table(String filename) throws FileNotFoundException {
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
                    throw new IllegalArgumentException("Invalid numbers in file");
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
        }
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


    public Table move(Direction direction) throws WrongMoveException {
        // Nalezy wykonac kopie, aby zwrocic zmodyfikowany stan tablicy nienaruszając obecnego
        Table before = this.clone();

        switch (direction) {
            case U: {
                if (this.zeroRow == 0) {
                    throw new WrongMoveException("Cannot move up");
                } else {
                    this.table[zeroIndex] = this.table[this.zeroIndex - this.cols];
                    this.table[this.zeroIndex - this.cols] = 0;

                    this.zeroIndex -= this.cols;
                    this.zeroRow -= 1;
                }
                break;
            }

            case D: {
                if (this.zeroRow == (this.rows - 1)) {
                    throw new WrongMoveException("Cannot move down");
                } else {
                    this.table[zeroIndex] = this.table[this.zeroIndex + this.cols];
                    this.table[this.zeroIndex + this.cols] = 0;

                    this.zeroIndex += this.cols;
                    this.zeroRow += 1;
                }
                break;
            }

            case L: {
                if (this.zeroCol == 0) {
                    throw new WrongMoveException("Cannot move left");
                } else {
                    this.table[zeroIndex] = this.table[this.zeroIndex - 1];
                    this.table[this.zeroIndex - 1] = 0;

                    this.zeroIndex -= 1;
                    this.zeroCol -= 1;
                }
                break;
            }

            case R: {
                if (this.zeroCol == (this.cols - 1)) {
                    throw new WrongMoveException("Cannot move right");
                } else {
                    this.table[zeroIndex] = this.table[this.zeroIndex + 1];
                    this.table[this.zeroIndex + 1] = 0;

                    this.zeroIndex += 1;
                    this.zeroCol += 1;
                }
                break;
            }

        }

        // Przywróć poprzedni stan tablicy i zwróć zapisaną kopię
        Table after = this.clone();
        this.table = before.table;
        this.zeroIndex = before.zeroIndex;
        this.zeroCol = before.zeroCol;
        this.zeroRow = before.zeroRow;

        return after;
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

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Table clone() {
        return new Table(this.table.clone(), this.rows, this.cols, this.zeroIndex, this.zeroRow, this.zeroCol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Table table1 = (Table) o;

        return new EqualsBuilder()
                .append(table, table1.table)
                .isEquals();
    }
}
