public class Hamming implements Metric{
    @Override
    public int calc(Node node) {
        int distance = 0;

        if (node.getTable().getTable()[node.getTable().getTable().length - 1] != 0) {
            distance++;
        }

        for (int i = 0; i < node.getTable().getTable().length -1; i++) {
            if (node.getTable().getTable()[i] != i + 1) {
                distance ++;
            }
        }

        return distance;
    }
}
