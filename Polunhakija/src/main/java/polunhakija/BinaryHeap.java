
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
            
            if(heap[i].distance > heap[left].distance 
                    || heap[i].distance > heap[right].distance) {
                
                if(left < right) {
                    swap(i, left);
                    heapify(left);
                } 
                
                else {
                    swap(i, right);
                    heapify(right);
                }                
            }
        }
    }
    
    public Node heapMin() {
        return heap[1];
    }
    
    public boolean isEmpty() {
        return (heapSize == 0);
    }
    
    public void insert(Node node) {
        heapSize = heapSize + 1;
        int i = heapSize;
        
        while(i > 1 && heap[parent(i)].distance > node.distance) {
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = node;
    }
    
    public Node remove(){
        Node removed = heap[1];
        heap[1] = heap[heapSize];
        heapSize = heapSize - 1;
        heapify(1);
        return removed;
    }
    
    
    public void testPrint() {
        System.out.println("heapSize: " + heapSize);
        for(int i = 0; i < heapSize; i++) {
            System.out.println("Parent: " + heapMin()
                    + " LEFT CHILD: " + heap[left(i)]
                    + " RIGHT Child: " + heap[right(i)]
            );
        }
        System.out.println("---");
        for (int i = 0; i < heapSize + 4; i++) {
            System.out.println(heap[i]);
        }
    }
    
}
