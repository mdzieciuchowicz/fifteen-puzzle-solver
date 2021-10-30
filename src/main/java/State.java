public class State {
    private Table table;
    private int treeDepth;

    public State(Table table, int treeDepth) {
        this.table = table;
        this.treeDepth = treeDepth;
    }

    public Table getTable() {
        return table;
    }
}
