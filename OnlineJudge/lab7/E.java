package lab7;

import java.util.Scanner;

public class E {
    static class BinarySearchTree {
        private Node root;

        static class Node {
            long element;
            Node left;
            Node right;
            int height;
            int size;

            Node(long d) {
                element = d;
                height = 0;
                size = 1;
            }
        }

        int height(Node gen) {
            return gen == null ? -1 : gen.height;
        }

        int size(Node gen) {
            return gen == null ? 0 : gen.size;
        }

        BinarySearchTree() {
            root = null;
        }

        private Node findMin(Node gen) {
            if (gen == null) return null;
            while (gen.left != null) {
                gen = gen.left;
            }
            return gen;
        }

        private Node insert(long key, Node gen) {
            if (gen == null) return new Node(key);
            if (gen.element > key) {
                gen.left = insert(key, gen.left);
            } else if (gen.element < key) {
                gen.right = insert(key, gen.right);
            }
            return balance(gen);
        }

        private Node balance(Node gen) {
            if (gen == null) return null;
            if (height(gen.left) - height(gen.right) > 1) {
                if (height(gen.left.left) >= height(gen.left.right))
                    gen = rotate_left_left(gen);
                else gen = rotate_left_right(gen);
            } else if (height(gen.right) - height(gen.left) > 1) {
                if (height(gen.right.right) >= height(gen.right.left))
                    gen = rotate_right_right(gen);
                else gen = rotate_right_left(gen);
            }
            gen.height = Math.max(height(gen.left), height(gen.right)) + 1;
            gen.size = size(gen.left) + size(gen.right) + 1;
            return gen;
        }

        private Node rotate_right_left(Node gen) {
            gen.right = rotate_left_left(gen.right);
            return rotate_right_right(gen);
        }

        private Node rotate_right_right(Node gen) {

            Node k1 = gen.right;
            gen.right = k1.left;
            k1.left = gen;
            gen.height = Math.max(height(gen.right), height(gen.left)) + 1;
            k1.height = Math.max(height(k1.right), gen.height) + 1;

            k1.size = size(gen);
            gen.size = size(gen.left) + size(gen.right) + 1;
            return k1;
        }

        private Node rotate_left_right(Node gen) {
            gen.left = rotate_right_right(gen.left);
            return rotate_left_left(gen);
        }

        private Node rotate_left_left(Node gen) {
            Node k1 = gen.left;
            gen.left = k1.right;
            k1.right = gen;
            gen.height = Math.max(height(gen.left), height(gen.right)) + 1;
            k1.height = Math.max(height(k1.left), gen.height) + 1;

            k1.size = size(gen);
            gen.size = size(gen.left) + size(gen.right) + 1;
            return k1;

        }

        private Node remove(long key, Node gen) {
            if (gen == null) return null;
            if (gen.element > key) {
                gen.left = remove(key, gen.left);
            } else if (gen.element < key) {
                gen.right = remove(key, gen.right);
            } else if (gen.left != null && gen.right != null) {
                gen.element = findMin(gen.right).element;
                gen.right = remove(gen.element, gen.right);
            } else gen = (gen.left != null) ? gen.left : gen.right;
            return balance(gen);
        }

        private void printTree(Node gen) {
            if (gen != null) {
                printTree(gen.left);
                System.out.print(gen.element + "(" + size(gen) + ") ");
                printTree(gen.right);
            }
        }

        private void printTree() {
            printTree(root);
        }


      /*  Node findKth(Node gen, int k) {
            Stack<Node> stack = new Stack<>();
            Node p = gen;
            int i = 0;
            while (p != null || !stack.isEmpty()) if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                Node node = stack.pop();
                if (i++ == k) return node;
                p = node.right;
            }
            return null;
        }*/

        Long findKth(Node gen, int k) {
            int s = size(gen.left);
            if (s == k - 1)
                return gen.element;
            else if (k <= s)
                return findKth(gen.left, k);
            else return findKth(gen.right, k - s - 1);
        }
    }

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int len = in.nextInt();
        int nodes = in.nextInt();
        int operands = len - nodes + 1;
        long[] numbers = new long[len];
        for (int i = 0; i < len; i++) {
            numbers[i] = in.nextLong();
        }
        int flag;
        BinarySearchTree bst = new BinarySearchTree();
        for (flag = 0; flag < nodes; flag++) {
            bst.root = bst.insert(numbers[flag], bst.root);
        }
        flag--;
        for (int i = 0; i < operands; i++) {
           // bst.printTree();
            System.out.print(bst.findKth(bst.root, in.nextInt()));
            if (i != operands - 1) {
                System.out.println();
                flag++;
                bst.root = bst.remove(numbers[flag - nodes], bst.root);
                bst.root = bst.insert(numbers[flag], bst.root);
            }
        }
    }
}
