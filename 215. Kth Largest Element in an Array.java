//1
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}

//2 random quick select, avg O(n)
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
    // 逆序partition
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

class Solution {
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        return randomQuickSelect(nums, 0, len - 1, len - k);
    }
    
    // return 0 indexed, kth smallest element
    private int randomQuickSelect(int[] nums, int start, int end, int k) {
        Random rand = new Random();
        int pivotIdx = rand.nextInt(end - start + 1) + start;
        int pivot = nums[pivotIdx];
        swap(nums, pivotIdx, end);
        int left = start;
        
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                swap(nums, left, i);
                left++;
            }
        }
        
        // swap the pivot to its correct position
        swap(nums, left, end);
        if (left == k) {
            return nums[left];
        } else if (left > k) {
            return randomQuickSelect(nums, start, left - 1, k);
        } else {
            return randomQuickSelect(nums, left + 1, end, k);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

//3 PriorityQueue, O(nlgk)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : nums) {
            if (pq.size() < k) {
                pq.offer(i);
            } else if (i > pq.peek()) {
                pq.poll();
                pq.offer(i);
            }
        }
        return pq.peek();
    }
}
