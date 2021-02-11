
package polunhakija;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class BinaryHeapTest {
    
    private BinaryHeap heap;
    
    @Before
    public void setUp() {
        heap = new BinaryHeap(100);
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
    
}
