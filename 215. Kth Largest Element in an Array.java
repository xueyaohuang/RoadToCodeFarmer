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

// import java.util.concurrent.ThreadLocalRandom;
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    private int quickSelect(int[] nums, int start, int end, int k) {
        Random rand = new Random();
        // int randomNum = ThreadLocalRandom.current().nextInt(start, end + 1);
        int randomNum = rand.nextInt(end - start + 1) + start;
        int pivot = nums[randomNum];
        swap(nums, randomNum, end);
        int left = start;
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                swap(nums, left++, i);
            }
        }
        swap(nums, left, end);
        if (left == k) {
            return nums[k];
        }
        else if (left > k) {
            return quickSelect(nums, start, left - 1, k);
        }
        else {
            return quickSelect(nums, left + 1, end, k);
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
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }
        int min = 0;
        for (int i = k; i < nums.length; i++) {
            min = pq.peek();
            if (nums[i] > min) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }
        return pq.peek();
    }
}
