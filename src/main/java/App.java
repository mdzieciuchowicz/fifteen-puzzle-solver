public class App {
    public static void main(String[] args) {
        System.out.println("hello world");

        Table table = new Table("D:\\Pobrane\\4x4_01_00001.txt");
        System.out.println(table.getTableAsString());
        System.out.println("Rows: " + table.getRows());
        System.out.println("Cols: " + table.getCols());
        System.out.println("ZeroIndex: " + table.getZeroIndex());
        System.out.println("ZeroCol: " + table.getZeroCol());
        System.out.println("ZeroRow: " + table.getZeroRow());
        System.out.println(table.checkIfSolved());

        try {
            Table newMove = table.move(Direction.D);
            System.out.println(newMove.getTableAsString());
            System.out.println("ZeroIndex: " + newMove.getZeroIndex());
            System.out.println("ZeroCol: " + newMove.getZeroCol());
            System.out.println("ZeroRow: " + newMove.getZeroRow());
            System.out.println(newMove.checkIfSolved());

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
