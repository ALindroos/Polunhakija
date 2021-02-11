
package polunhakija;

public class BinaryHeap {
    
    private Node[] heap;
    private int heapSize;
    
    
    public BinaryHeap(int size) {
        this.heapSize = 0;
        heap = new Node[size];
    }
    
    private int parent(int i) {
        return (i / 2);
    }
    
    private int left(int i) {
        return (i * 2);
    }
    
    private int right(int i) {
        return (i * 2) + 1;
    }
    
    private void swap(int apos, int bpos) {
        Node temp = heap[apos];
        heap[apos] = heap[bpos];
        heap[bpos] = temp;
    }
   
    
    private void heapify(int i) {
        int left = left(i);
        int right = right(i);
        int min;
        
        if (right <= heapSize) {
            
            if (heap[left].distance < heap[right].distance) {
                min = left;
            } else {
                min = right;
            }
            
            if (heap[i].distance > heap[min].distance) {
                swap(i, min);
                heapify(min);
            }
            
        } else if (left == heapSize && heap[i].distance > heap[left].distance) {
            swap(i,left);
        }
          
    }
    
    public Node heapMin() {
        return heap[1];
    }
    
    public boolean isEmpty() {
        return (heapSize == 0);
    }
    
    public int size() {
        return heapSize;
    }

    public void insert(Node node) {
        if (node == null) {
           return;
        }
        
        heapSize = heapSize + 1;
        int i = heapSize;
        
        while(i > 1 && heap[parent(i)].distance > node.distance) {
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = node;
    }
    
    public Node remove(){
        if (size() == 0) {
            return null;
        }
        Node removed = heap[1];
        heap[1] = heap[heapSize];
        heapSize = heapSize - 1;
        heapify(1);
        return removed;
    }
    
    
    public void testPrint() {
        System.out.println("heapSize: " + heapSize);
        for (int i = 1; i <= heapSize; i++) {
            System.out.println(heap[i].distance);
        }
        System.out.println("---");
    }
    
}
