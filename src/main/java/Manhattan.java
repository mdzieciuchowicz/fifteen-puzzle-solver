public class Manhattan implements Metric{
    @Override
    public double calc(Node node) {
        int distance = 0;
        int x, y, goalX, goalY;

        for (int i = 0; i < node.getCurrentState().getTable().getTable().length; i++) {
            if (node.getCurrentState().getTable().getTable()[i] != i + 1 && node.getCurrentState().getTable().getTable()[i] != 0) {
                //przeliczanie na współrzędne w 2 wymiarach
                goalX = (node.getCurrentState().getTable().getTable()[i] - 1) % node.getCurrentState().getTable().getCols();
                goalY = (node.getCurrentState().getTable().getTable()[i] - 1) / node.getCurrentState().getTable().getRows();
                x = i % node.getCurrentState().getTable().getCols();
                y = i / node.getCurrentState().getTable().getRows();

                distance += (Math.abs(goalX - x) + Math.abs(goalY - y));
            }
        }

        return distance;
    }
}
