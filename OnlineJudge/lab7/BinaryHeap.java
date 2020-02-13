package lab7;

public class BinaryHeap {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private int[] array;

    private BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    private BinaryHeap(int capacity) {
        currentSize = 0;
        array = new int[capacity + 1];
    }

    public BinaryHeap(int[] items) {
        currentSize = items.length;
        array = new int[currentSize + 10];

        int i = 1;
        for (int item : items)
            array[i++] = item;
        buildHeap();
    }

    private void percolateDown(int hole) {
        int child;
        int tmp = array[hole];

        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize &&
                    array[child + 1] < (array[child]))
                child++;
            if (array[child] < tmp)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }

    private void insert(int next) {
        if (currentSize == array.length - 1)
            enlargeArray(array.length * 2 - 1);
        int hole = ++currentSize;
        for (array[0] = next; next < array[hole / 2]; hole /= 2) {
            array[hole] = array[hole / 2];
        }
        array[hole] = next;
    }

    private void enlargeArray(int newSize) {
        int[] old = array;
        array = new int[newSize];
        System.arraycopy(old, 0, array, 0, old.length);
    }

    private int deleteMin() {
        if (currentSize == 0) {
            System.out.println("Can't delete!");
            return -1;
        }
        int minItem = getMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--)
            percolateDown(i);
    }

    private int getMin(){
        return array[1];
    }

    public static void main(String[] args) {
        int numItems = 100;
        BinaryHeap h = new BinaryHeap();
        int i;
        for (i = 37; i != 0; i = (i + 37) % numItems) {
            System.out.println("i=  " + i);
            if (i%5==0)
            h.insert(i);
        }
        System.out.println(h.deleteMin());
        System.out.println(h.deleteMin());
        System.out.println(h.deleteMin());
    }
}

