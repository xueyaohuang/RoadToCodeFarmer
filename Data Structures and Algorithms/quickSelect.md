从unordered array中选出第k小的数，quick select的平均时间复杂度是O(n).  
加上 random 会更快, java 7 以后，生成一个范围内的随机数用：
```
import java.util.concurrent.ThreadLocalRandom;
int rand = ThreadLocalRandom.current().nextInt(start, end + 1);
```

例子：LC 215 Kth Largest Element in an Array

```
import java.util.concurrent.ThreadLocalRandom;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums.length < 2) {
            return nums[0];
        }
        
        int start = 0;
        int end = nums.length - 1;
        int pivotIdx = partition(nums, start, end);
        
        while (pivotIdx + 1 != k && start < end) {
            if (pivotIdx + 1 < k) {
                start = pivotIdx + 1;
            } else {
                end = pivotIdx - 1;
            }
            pivotIdx = partition(nums, start, end);
        }
        return nums[pivotIdx];
    }
    
    private int partition(int[] nums, int start, int end) {
        int pivotIdx = start;
        int rand = ThreadLocalRandom.current().nextInt(start, end + 1);
        swap(nums, rand, end);
        int pivot = nums[end];
        for (int i = start; i < end; i++) {
            if (pivot < nums[i]) {
                swap(nums, pivotIdx, i);
                pivotIdx++;
            }
        }
        swap(nums, pivotIdx, end);
        return pivotIdx;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```
