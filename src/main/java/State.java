public class State {
    private Table table;
    private int treeDepth;
    private String movesHistory;

    public State(Table table, int treeDepth, String movesHistory) {
        this.table = table;
        this.treeDepth = treeDepth;
        this.movesHistory = movesHistory;
    }

    public Table getTable() {
        return table;
    }

    public int getTreeDepth() {
        return treeDepth;
    }

    public String getMovesHistory() {
        return movesHistory;
    }
}
