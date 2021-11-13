public class Manhattan implements Metric{
    @Override
    public int calc(Node node) {
        int distance = 0;
        int x, y, goalX, goalY;

        for (int i = 0; i < node.getTable().getTable().length; i++) {
            if (node.getTable().getTable()[i] != i + 1 && node.getTable().getTable()[i] != 0) {
                //przeliczanie na współrzędne w 2 wymiarach
                goalX = (node.getTable().getTable()[i] - 1) % node.getTable().getCols();
                goalY = (node.getTable().getTable()[i] - 1) / node.getTable().getRows();
                x = i % node.getTable().getCols();
                y = i / node.getTable().getRows();

                distance += (Math.abs(goalX - x) + Math.abs(goalY - y));
            }
        }

        return distance;
    }
}
