public class Hamming implements Metric{
    @Override
    public double calc(Node node) {
        int distance = 0;

        if (node.getCurrentState().getTable().getTable()[node.getCurrentState().getTable().getTable().length - 1] != 0) {
            distance++;
        }

        for (int i = 0; i < node.getCurrentState().getTable().getTable().length -1; i++) {
            if (node.getCurrentState().getTable().getTable()[i] != i + 1) {
                distance ++;
            }
        }

        return distance;
    }
}
