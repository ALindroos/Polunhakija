
package polunhakija;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class BinaryHeapTest {
    
    private BinaryHeap heap;
    private Node a;
    private Node b;
    private Node c;
    
    @Before
    public void setUp() {
        heap = new BinaryHeap(100); 
        a = new Node(2,2);
        a.priority = 3;
        b = new Node(1,1);
        b.priority = 1;
        c = new Node(3,3);
        c.priority = 5;
    }
    
    @Test
    public void heapIsEmpty() {
        assertEquals(heap.isEmpty(), true);
        heap.insert(new Node(1,1));
        assertEquals(heap.isEmpty(), false);
        
    }
    
    @Test
    public void heapSizeAdd() {
        assertEquals(heap.size(), 0);
        heap.insert(new Node(1,1));
        assertEquals(heap.size(), 1);
    }
    
    @Test
    public void heapSizeAddSub() {
        heap.insert(new Node(1,1));
        heap.insert(new Node(2,2));
        assertEquals(heap.size(), 2);
        heap.remove();
        assertEquals(heap.size(), 1);
    }
    
    @Test
    public void heapSizeNonNeg() {
        assertEquals(heap.size(), 0);
        heap.remove();
        assertEquals(heap.size(), 0);
    }    
    
    @Test
    public void cantInsertNull() {
        assertEquals(heap.size(), 0);
        Node n = null;
        heap.insert(n);
        assertEquals(heap.size(), 0);
    }
    
    @Test
    public void heapMinCorrect() {
        assertEquals(heap.heapMin(), null);
        heap.insert(a);
        heap.insert(b);
        assertEquals(heap.heapMin(), b);
    }
    
    @Test
    public void heapifyCorrectWithSeqValues() {
        
        heap.insert(b);
        for (int i = 2; i < 5; i++) {
            Node n = new Node(i, i);
            n.priority = i;
            heap.insert(n);
        }
        assertEquals(heap.remove(), b);
    }
    
    
    @Test
    public void heapifyVaryingValues() {
        heap.insert(b); // 1
        heap.insert(c); // 5
        heap.insert(b); // 3
        heap.insert(a); // 1
        
        assertEquals(heap.remove(), b);
        assertEquals(heap.remove(), b);        
    }  
}
