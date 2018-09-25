## Bucket Sort
桶排序和归并排序有那么点点类似，也使用了归并的思想。大致步骤如下：
1. 设置一个定量的数组当作空桶。
2. Divide - 从待排序数组中取出元素，将元素按照一定的规则塞进对应的桶子去。
3. 对每个非空桶进行排序，通常可在塞元素入桶时进行插入排序。
4. Conquer - 从非空桶把元素再放回原来的数组中。
5. Bucket sort对数据有要求，一般是均匀分布在某一个范围内。
6. 桶排序非常快,但是同时也非常耗空间,基本上是最耗空间的一种排序算法.

### merge sort
```
import java.util.*;

public class MergeSort {
    
    public static void merge(int[] nums, int l, int mid, int r) {
        
        int lena = mid - l + 1;
        int lenb = r - mid;
        int[] a = new int[lena];
        int[] b = new int[lenb];
        
        for (int i = 0; i < lena; i++) {
            a[i] = nums[l + i];
        }
        for (int i = 0; i < lenb; i++) {
            b[i] = nums[mid + 1 + i];
        }

        /* Merge the temp arrays */
        
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0;
        // Initial index of merged subarry array 
        int k = l;
        
        while (i < lena && j < lenb) { 
            if (a[i] <= b[j]) { 
                nums[k] = a[i]; 
                i++; 
            } else { 
                nums[k] = b[j]; 
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of a[] if any */
        while (i < lena) { 
            nums[k] = a[i]; 
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of b[] if any */
        while (j < lenb) { 
            nums[k] = b[j]; 
            j++; 
            k++; 
        }

    } 

    public static void sort(int[] nums, int l, int r) {
        
        if (l < r) {
            int mid = l + (r - l) / 2;
            sort(nums, l, mid);
            sort(nums, mid + 1, r);
            merge(nums, l, mid, r);
        }
        
    }
    
    public static void mergeSort(int[] nums) {
        int len = nums.length;
        sort(nums, 0, len - 1);
    }
  
    public static void main(String args[]) { 
        int[] nums = {4,6,7,3,4,-4,-2,-78}; 
  
        System.out.println("Given Array"); 
        System.out.println(Arrays.toString(nums));
  
        mergeSort(nums); 
  
        System.out.println("\nSorted array"); 
        System.out.println(Arrays.toString(nums));
    } 
}
```

### quick sort
```
import java.util.*;

public class QuickSort {
    
    public static int partition(int[] nums, int left, int right) {
        
        int pivot = nums[right];  
        int i = left - 1; // index of smaller element
        
        for (int j = left; j < right; j++) { 
            // If current element is smaller than or equal to pivot 
            if (nums[j] <= pivot) {
                i++; 
                swap(nums, i, j);
            } 
        } 
  
        // swap nums[i+1] and pivot
        swap(nums, i + 1, right);
  
        return i + 1; 
    }
    
    public static void sort(int nums[], int left, int right) {
        
        if (left < right) { 
            // pivotIdx is partitioning index, arr[pi] is now at right place
            int pivotIdx = partition(nums, left, right); 
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(nums, left, pivotIdx - 1); 
            sort(nums, pivotIdx + 1, right); 
        } 
    }
    
    public static void quickSort(int[] nums) {
        int len = nums.length;
        sort(nums, 0, len - 1); 
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String args[]) { 
        int[] nums = {4,6,7,3,4,-4,-2,-78}; 
  
        System.out.println("Given Array"); 
        System.out.println(Arrays.toString(nums));
  
        quickSort(nums); 
  
        System.out.println("\nSorted array"); 
        System.out.println(Arrays.toString(nums));
    } 
}

```

### insertion sort
```
import java.util.*;

public class InsertionSort {
    
    public static void insertionSort(int[] nums) {
        
        int len = nums.length;
        
        for (int i = 1; i < len; i++) {
            
            int key = nums[i];
            int j = i - 1;
 
            // Move elements of arr[0..i-1], that are greater than key, to one position
            // ahead of their current position
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
    }
  
    public static void main(String args[]) { 
        int[] nums = {4,6,7,3,4,-4,-2,-78}; 
  
        System.out.println("Given Array"); 
        System.out.println(Arrays.toString(nums));
  
        insertionSort(nums); 
  
        System.out.println("\nSorted array"); 
        System.out.println(Arrays.toString(nums));
    } 
}
```
