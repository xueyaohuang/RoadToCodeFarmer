//1
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}

//2 
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    private int quickSelect(int[] nums, int start, int end, int k) {
        int pivot = nums[end];
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

//3
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k);
        for (int num : nums) {
            if (pq.size() < k) {
                pq.add(num);
            }
            else {
                if (num > pq.peek()) {
                    pq.poll();
                    pq.add(num);
                }
            }
        }
        return pq.peek();
    }
}
