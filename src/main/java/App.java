public class App {
    public static void main(String[] args) {
        System.out.println("hello world");

        Table table = new Table("D:\\Pobrane\\4x4_01_00001.txt");
        System.out.println(table.getTableAsString());
        System.out.println("Rows: " + table.getRows());
        System.out.println("Cols: " + table.getCols());
    }
}
