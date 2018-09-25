* Java implementation of min heap
* [Excellent introduction](https://www.geeksforgeeks.org/binary-heap/)

### Array implementation

```
import java.util.*;

public class MinHeap {
    
    private int[] heapArr; // array of elements in heap
    private int capacity; // maximum possible size of min heap
    private int heapSize; // Current number of elements in min heap
    
    // Constructor: Builds a heap from a given array a[] of given size
    public MinHeap(int cpct) {
        heapSize = 0;
        capacity = cpct;
        heapArr = new int[capacity];
    }
    
    public MinHeap(int[] nums) {
        heapArr = nums;
        capacity = heapArr.length;
        heapSize = heapArr.length;
        buildMinHeap();
    }
    
    // Inserts a new key 'k'
    public void insertKey(int key) {
        if (heapSize == capacity) {
            return;
        }
        // First insert the new key at the end
        heapSize++;
        int i = heapSize - 1;
        heapArr[i] = key;
     
        // Fix the min heap property if it is violated
        while (i != 0 && heapArr[parent(i)] > heapArr[i]) {
           swap(heapArr, parent(i), i);
           i = parent(i);
        }
    }
     
    // Decreases value of key at index 'i' to new_val.  
    // It is assumed that new_val is smaller than harr[i].
    public void decreaseKey(int i, int val) {
        heapArr[i] = val;
        while (i != 0 && heapArr[parent(i)] > heapArr[i]) {
           swap(heapArr, parent(i), i);
           i = parent(i);
        }
    }
    
    // Method to remove minimum element (or root) from min heap
    public int extractMin() {
        if (heapSize <= 0) {
            return Integer.MIN_VALUE;
        }
        if (heapSize == 1)
        {
            heapSize--;
            return heapArr[0];
        }
        // Store the minimum value, and remove it from heap
        int root = heapArr[0];
        heapArr[0] = heapArr[heapSize-1];
        heapSize--;
        minHeapify(0);
        return root;
    }
    
    // get the min value, but not remove
    public int getMin() {
        return heapArr[0];
    }
    
    // This function deletes key at index i. 
    // It first reduced value to minus infinite, then calls extractMin()
    public void deleteKey(int i) {
        decreaseKey(i, Integer.MIN_VALUE);
        extractMin();
    }
    
    // A recursive method to heapify a subtree with the root at given index
    // This method assumes that the subtrees are already heapified
    public void minHeapify(int i) {
        
        int l = left(i);
        int r = right(i);
        int smallest = i;
        
        if (l < heapSize && heapArr[l] < heapArr[i]) {
            smallest = l;
        }
            
        if (r < heapSize && heapArr[r] < heapArr[smallest]) {
            smallest = r;
        }
            
        if (smallest != i) {
            swap(heapArr, i, smallest);
            minHeapify(smallest);
        }
    }
    
    // Build heap time complexity: O(n).
    // https://www.geeksforgeeks.org/time-complexity-of-building-a-heap/
    // Heapify procedure can be applied to a node only if its children nodes are heapified. 
    // So the heapification must be performed in the bottom up order.
    // Since the structure of heapArr is a complete binary tree, half of the element does not have 
    // left or right, so we just start from heapSize / 2.
    public void buildMinHeap() {
        for (int i = heapSize / 2; i >= 0; i--) {
            minHeapify(i);
        }
    }
    
    public void heapSort(int[] nums) {
        
    }
    
    public int parent(int i) {
        return (i - 1) / 2;
    }
 
    // to get index of left child of node at index i
    public int left(int i) {
        return 2 * i + 1;
    }
 
    // to get index of right child of node at index i
    public int right(int i) {
        return 2 * i + 2;
    }
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
  
    public static void main(String args[]) {
        
        int[] nums = {0,1,2,3,4,5,6,7,8,9};
        
        
        MinHeap mh = new MinHeap(nums);
        System.out.println("Capacity: " + mh.capacity);
        System.out.println("Size: " + mh.heapSize);
        
        int min = mh.extractMin();
        System.out.println(min);
        System.out.println("Size: " + mh.heapSize);
        

        // mh.decreaseKey(1, -1);
        // int min = mh.getMin();
        // System.out.println(min);
        
        // mh.extractMin();
        // System.out.println(mh.getMin());
        
        // mh.deleteKey(0);
        // min = mh.getMin();
        // System.out.println(min);
        
    } 
}
```

### ArrayList implementation
```
import java.util.ArrayList;

public class MinHeap {

    private ArrayList<Integer> list;

    public MinHeap() {
        this.list = new ArrayList<Integer>();
    }

    public MinHeap(ArrayList<Integer> items) {
        this.list = items;
        buildHeap();
    }

    public void insert(int item) {
        list.add(item);
        int i = list.size() - 1;
        int parent = parent(i);

        while (parent != i && list.get(i) < list.get(parent)) {
            swap(i, parent);
            i = parent;
            parent = parent(i);
        }
    }

    public void buildHeap() {
        for (int i = list.size() / 2; i >= 0; i--) {
            minHeapify(i);
        }
    }

    public int extractMin() {

        if (list.size() == 0) {
            throw new IllegalStateException("MinHeap is EMPTY");
        } else if (list.size() == 1) {
            int min = list.remove(0);
            return min;
        }

        // remove the last item ,and set it as new root
        int min = list.get(0);
        int lastItem = list.remove(list.size() - 1);
        list.set(0, lastItem);

        // bubble-down until heap property is maintained
        minHeapify(0);

        // return min key
        return min;
    }

    public void decreaseKey(int i, int key) {

        if (list.get(i) < key) {
            throw new IllegalArgumentException("Key is larger than the original key");
        }

        list.set(i, key);
        int parent = parent(i);

        // bubble-up until heap property is maintained
        while (i > 0 && list.get(parent) > list.get(i)) {
            swap(i, parent);
            i = parent;
            parent = parent(parent);
        }
    }

    private void minHeapify(int i) {

        int left = left(i);
        int right = right(i);
        int smallest = -1;

        // find the smallest key between current node and its children.
        if (left <= list.size() - 1 && list.get(left) < list.get(i)) {
            smallest = left;
        } else {
            smallest = i;
        }

        if (right <= list.size() - 1 && list.get(right) < list.get(smallest)) {
            smallest = right;
        }

        // if the smallest key is not the current key then bubble-down it.
        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    public int getMin() {
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void swap(int i, int parent) {
        int temp = list.get(parent);
        list.set(parent, list.get(i));
        list.set(i, temp);
    }

}
```
