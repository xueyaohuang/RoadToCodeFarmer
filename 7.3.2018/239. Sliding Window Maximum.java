// naive solution, not O(n).
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = maxAmongK(nums, i, k);
        }
        return res;
    }
    
    private int maxAmongK(int[] nums, int start, int k) {
        int max = nums[start];
        for (int i = start; i < start + k; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }
}

// O(n) solution.
