package world.hello.helloworld.xns;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Trees {

    public static void main(String[] args) {

        Node x = new Node();
        x.data = "X";

        Node a = new Node();
        a.data = "A";

        Node b = new Node();
        b.data = "B";

        Node c = new Node();
        c.data = "C";

        Node p = new Node();
        p.data = "P";

        Node q = new Node();
        q.data = "Q";

        Node r = new Node();
        r.data = "R";

        Node s = new Node();
        s.data = "S";

        x.left = a;
        a.left = b;
        a.right = c;

        x.right = p;
        p.left = q;
        p.right = r;

        r.right = s;

        System.out.println("\nBreath first: ");
        bft(x);

        System.out.println("\n\nDF pre order: ");
        dftPreOrder(x);

        System.out.println("\n\nDF in order: ");
        dftInOrder(x);

        System.out.println("\n\nDF post order: ");
        dftPostOrder(x);

        List<Node> path = new ArrayList<>();
        getPath(x, q, path);

        StringBuilder pathStrBuilder = new StringBuilder();
        pathStrBuilder.append("\n\nPath from X to Q : ");
        for (int i = path.size() - 1; i >= 0; i--) {
            Node node = path.get(i);
            pathStrBuilder.append(" ").append(node.data);
        }
        System.out.println(pathStrBuilder);

        System.out.println("\n");
    }

    private static void bft(Node root) {

        LinkedList<Node> queue = new LinkedList<>();

        if (root != null) {
            queue.addLast(root);
        }

        while (!queue.isEmpty()) {
            Node node = queue.removeFirst();
            visit(node);
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }
    }

    private static void dftPostOrder(Node root) {

        if (root.left != null) {
            dftPostOrder(root.left);
        }

        if (root.right != null) {
            dftPostOrder(root.right);
        }

        visit(root);
    }

    private static void dftPreOrder(Node root) {

        visit(root);

        if (root.left != null) {
            dftPreOrder(root.left);
        }

        if (root.right != null) {
            dftPreOrder(root.right);
        }
    }

    private static void dftInOrder(Node root) {

        if (root.left != null) {
            dftInOrder(root.left);
        }

        visit(root);

        if (root.right != null) {
            dftInOrder(root.right);
        }
    }

    private static void visit(Node node) {
        System.out.print(node.data + " ");
    }

    private static boolean getPath(Node rootNode, Node dest, List<Node> path) {
        if (rootNode == null) {
            return false;
        }
        if (rootNode == dest) {
            path.add(rootNode);
            return true;
        }
        boolean leftCheck = getPath(rootNode.left, dest, path);
        boolean rightCheck = getPath(rootNode.right, dest, path);
        if (leftCheck || rightCheck) {
            path.add(rootNode);
            return true;
        }
        return false;
    }

    private static class Node {
        String data;
        Node left;
        Node right;
    }
}
