import java.util.LinkedList;

public class RedBlackTree {
    private NodeRBT root;
    private final NodeRBT NIL;

    public RedBlackTree() {
        NIL = new NodeRBT(null);
        NIL.isRed = false;
        this.root = NIL;
    }

    public void addTask(String date, String task) {
        NodeRBT existingNode = searchNode(root, date);
        if (existingNode != NIL) {
            existingNode.tasks.add(task);
        } else {
            NodeRBT newNode = new NodeRBT(date);
            newNode.tasks.add(task);
            insert(newNode);
        }
    }

    public LinkedList<String> getTasks(String date) {
        NodeRBT node = searchNode(root, date);
        if (node != NIL) {
            return node.tasks;
        }
        return null;
    }

    private NodeRBT searchNode(NodeRBT node, String date) {
        while (node != NIL && !date.equals(node.date)) {
            if (date.compareTo(node.date) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    private void insert(NodeRBT newNode) {
        NodeRBT parent = null;
        NodeRBT current = root;

        while (current != NIL) {
            parent = current;
            if (newNode.date.compareTo(current.date) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        newNode.parent = parent;
        if (parent == null) {
            root = newNode;
        } else if (newNode.date.compareTo(parent.date) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        newNode.left = NIL;
        newNode.right = NIL;
        newNode.isRed = true;

        fixInsertion(newNode);
    }

    private void fixInsertion(NodeRBT node) {
        while (node.parent != null && node.parent.isRed) {
            if (node.parent == node.parent.parent.left) {
                NodeRBT uncle = node.parent.parent.right;
                if (uncle.isRed) {
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        rotateLeft(node);
                    }
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    rotateRight(node.parent.parent);
                }
            } else {
                NodeRBT uncle = node.parent.parent.left;
                if (uncle.isRed) {
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    rotateLeft(node.parent.parent);
                }
            }
        }
        root.isRed = false;
    }

    private void rotateLeft(NodeRBT node) {
        NodeRBT rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != NIL) {
            rightChild.left.parent = node;
        }
        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }
        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rotateRight(NodeRBT node) {
        NodeRBT leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != NIL) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }
        leftChild.right = node;
        node.parent = leftChild;
    }

    public void printTree() {
        System.out.println("Current Red-Black Tree:");
        printSubTree(root, "", true);
    }

    private void printSubTree(NodeRBT node, String indent, boolean isRight) {
        if (node != NIL) {
            System.out.println(indent + (isRight ? "R---- " : "L---- ") + 
                               (node.isRed ? "(Red) " : "(Black) ") + node.date);
            printSubTree(node.left, indent + (isRight ? "     " : "|    "), false);
            printSubTree(node.right, indent + (isRight ? "     " : "|    "), true);
        }
    }
}
