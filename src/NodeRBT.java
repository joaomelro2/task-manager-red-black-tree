import java.util.LinkedList;

public class NodeRBT {
    String date;
    LinkedList<String> tasks;
    NodeRBT left, right, parent;
    boolean isRed;

    public NodeRBT(String date) {
        this.date = date;
        this.tasks = new LinkedList<>();
        this.left = null;
        this.right = null;
        this.parent = null;
        this.isRed = true;
    }
}
