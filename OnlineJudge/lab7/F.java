package lab7;

import java.io.*;
import java.util.StringTokenizer;

public class F {
    static class BinarySearchTree {
        private Node root;

        static class Node {
            int element;
            Node left;
            Node right;
            int height;

            Node(int d) {
                element = d;
                height = 0;
            }
        }


        int height(Node gen) {
            return gen == null ? -1 : gen.height;
        }

        BinarySearchTree() {
            root = null;
        }

        private boolean isEmpty() {
            return root == null;
        }

        private Node findMin(Node gen) {
            if (gen == null) return null;
            while (gen.left != null) {
                gen = gen.left;
            }
            return gen;
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

            return gen;
        }

        private Node rotateRightLeft(Node gen) {
            gen.right = rotateLeftLeft(gen.right);
            return rotateRightRight(gen);
        }

        private Node rotateRightRight(Node gen) {
            Node k1 = gen.right;
            gen.right = k1.left;
            k1.left = gen;
            gen.height = Math.max(height(gen.right), height(gen.left)) + 1;
            k1.height = Math.max(height(k1.right), gen.height) + 1;
            return k1;
        }

        private Node rotateLeftRight(Node gen) {
            gen.left = rotateRightRight(gen.left);
            return rotateLeftLeft(gen);
        }

        private Node rotateLeftLeft(Node gen) {
            Node k1 = gen.left;
            gen.left = k1.right;
            k1.right = gen;
            gen.height = Math.max(height(gen.left), height(gen.right)) + 1;
            k1.height = Math.max(height(k1.left), gen.height) + 1;
            return k1;
        }

        private Node remove(long key, Node gen) {
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


        private int delete(int key, Node gen) {
            int p = predecessorQuery(key, gen);
            int s = successorQuery(key, gen);
            if (p != -1 && s != -1) {
                if ((key - p) <= (s - key)) {
                    root = remove(p, gen);
                    return key - p;
                } else {
                    root = remove(s, gen);
                    return s - key;
                }
            } else if (p != -1) {
                root = remove(p, gen);
                return key - p;
            } else {
                root = remove(s, gen);
                return s - key;
            }
        }
    }

    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();

        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);


    public static void main(String[] args) {
        int operands = in.nextInt();
        BinarySearchTree adopters = new BinarySearchTree();
        BinarySearchTree pets = new BinarySearchTree();
        long sum = 0L;
        for (int i = 0; i < operands; i++) {
            boolean isPet = (in.nextInt() == 0);
            int id = in.nextInt();
            if (isPet)
                if (adopters.isEmpty())
                    pets.root = pets.insert(id, pets.root);
                else sum += happiness(id, adopters);
            else {
                if (pets.isEmpty())
                    adopters.root = pets.insert(id, adopters.root);
                else sum += happiness(id, pets);
            }
        }
        out.println(sum);
        out.close();
    }

    private static int happiness(int key, BinarySearchTree bst) {
        return bst.delete(key, bst.root);
    }
}
