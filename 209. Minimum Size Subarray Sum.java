// O(n), 一头进，一头出
// sliding window
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
                res = Math.min(res, i - start + 1);
                sum -= nums[start];
                start++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}

// O(nlgn)
// try and verify
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int start = 1;
        int end = nums.length;
        int min = nums.length;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (windowExists(s, nums, mid)) {
                end = mid;
                min = mid;
            } else {
                start = mid + 1;
            }
        }
        if (windowExists(s, nums, min)) {
            return min;
        }
        return 0;
    }
    
    private boolean windowExists(int s, int[] nums, int size) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (i >= size) {
                sum -= nums[i - size];
            }
            sum += nums[i];
            if (sum >= s) {
                return true;
            }
        }
        return false;
    }
}
