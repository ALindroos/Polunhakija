
package polunhakija;

/**
 * custom implementation of minimum binary heap for Nodes
 * uses node distance as comparison factor
 *
 */
public class BinaryHeap {
    
    private Node[] heap;
    private int heapSize;
    
    /**
     * Initialise heap
     * @param size for the worst possible scenario that all nodes need to
     * be examined the heap size should be set to the size of the grid
     */
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
            
            if (heap[left].priority < heap[right].priority) {
                min = left;
            } else {
                min = right;
            }
            
            if (heap[i].priority > heap[min].priority) {
                swap(i, min);
                heapify(min);
            }
            
        } else if (left == heapSize && heap[i].priority > heap[left].priority) {
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
        
        while(i > 1 && heap[parent(i)].priority > node.priority) {
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
}
