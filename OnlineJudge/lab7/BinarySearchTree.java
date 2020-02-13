package lab7;

public class BinarySearchTree {
    private Node root;

    static class Node {
        int element;
        Node left;
        Node right;
        int height;
        int size;

        Node(int d) {
            element = d;
            height = 0;
        }
    }

    private int size(Node gen) {
        return gen == null ? 0 : gen.size;
    }

    private int height(Node gen) {
        return gen == null ? -1 : gen.height;
    }

    private BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(Node r) {
        root = r;
    }

    private boolean contains(int key, Node gen) {
        if (gen == null) return false;
        if (gen.element > key)
            return contains(key, gen.left);
        else if (gen.element < key)
            return contains(key, gen.right);
        else return true;
    }

    private Node findMin(Node gen) {
        if (gen == null) return null;
        while (gen.left != null) {
            gen = gen.left;
        }
        return gen;
    }

    private Node findMax(Node gen) {
        if (gen != null)
            while (gen.right != null)
                gen = gen.right;
        return findMin(gen);
    }

    private Node insert(int key, Node gen) {
        if (gen == null) return new Node(key);
        if (gen.element > key)
            gen.left = insert(key, gen.left);
        else if (gen.element < key)
            gen.right = insert(key, gen.right);
        return balance(gen);
    }

    private Node balance(Node gen) {
        if (gen == null) return null;
        if (height(gen.left) - height(gen.right) > 1) {
            if (height(gen.left.left) >= height(gen.left.right))
                gen = rotateLeftLeft(gen);
            else gen = rotateLeftRight(gen);
        } else if (height(gen.right) - height(gen.left) > 1) {
            if (height(gen.right.right) >= height(gen.right.left))
                gen = rotateRightRight(gen);
            else gen = rotateRightLeft(gen);
        }
        gen.height = Math.max(height(gen.left), height(gen.right)) + 1;
        gen.size = size(gen.left) + size(gen.right) + 1;
        return gen;
    }

    private Node rotateRightLeft(Node a) {
        a.right = rotateLeftLeft(a.right);
        return rotateRightRight(a);
    }

    private Node rotateRightRight(Node a) {
        Node b = a.right;
        a.right = b.left;
        b.left = a;
        a.height = Math.max(height(a.right), height(a.left)) + 1;
        b.height = Math.max(height(b.right), a.height) + 1;
        a.size = size(a.left) + size(a.right) + 1;
        return b;
    }

    private Node rotateLeftRight(Node a) {
        a.left = rotateRightRight(a.left);
        return rotateLeftLeft(a);
    }

    private Node rotateLeftLeft(Node a) {
        Node b = a.left;
        a.left = b.right;
        b.right = a;
        a.height = Math.max(height(a.left), height(a.right)) + 1;
        b.height = Math.max(height(b.left), a.height) + 1;
        a.size = size(a.left) + size(a.right) + 1;
        return b;
    }

    private Node remove(int key, Node gen) {
        if (gen == null) return null;
        if (gen.element > key)
            gen.left = remove(key, gen.left);
        else if (gen.element < key)
            gen.right = remove(key, gen.right);
        else if (gen.left != null && gen.right != null) {
            gen.element = findMin(gen.right).element;
            gen.right = remove(gen.element, gen.right);
        } else gen = (gen.left != null) ? gen.left : gen.right;
        return balance(gen);
    }

    private int findKth(Node gen, int k) {
        int s = size(gen.left);
        if (s == k - 1)
            return gen.element;
        else if (k <= s)
            return findKth(gen.left, k);
        else return findKth(gen.right, k - s - 1);
    }

    private void printTree(Node gen) {
        if (gen != null) {
            printTree(gen.left);
            System.out.print(gen.element + " ");
            printTree(gen.right);
        }
    }

    private void printTree() {
        printTree(root);
    }

    private int predecessorQuery(int key, Node gen) {
        if (gen == null) return -1;
        if (gen.element > key) {
            return predecessorQuery(key, gen.left);
        } else if (gen.element < key) {
            int tmp = predecessorQuery(key, gen.right);
            return (tmp == -1) ? gen.element : tmp;
        } else return gen.element;
    }

    private int successorQuery(int key, Node gen) {
        if (gen == null) return -1;
        if (gen.element < key) {
            return successorQuery(key, gen.right);
        } else if (gen.element > key) {
            int tmp = successorQuery(key, gen.left);
            return (tmp == -1) ? gen.element : tmp;
        } else return gen.element;
    }

    private static BinarySearchTree createTree(int... numbers) {
        int len = numbers.length;
        BinarySearchTree BST = new BinarySearchTree();
        for (int i = 0; i < len; i++) {
            BST.root = BST.insert(numbers[i], BST.root);
        }
        return BST;
    }

    public static void main(String[] args) {
        createTree(322, 12, 213, 12312, 31123, 212, 21).printTree();
    }
}
