// O(n), 一头进，一头出
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int len = nums.length;
        int sum = 0;
        int j = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            while (sum >= s) {
                minLen = Math.min(minLen, i - j + 1);
                sum -= nums[j++];
            } 
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}

// O(nlgn)
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int start = 1;
        int end = nums.length;
        int min = 0;
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
        if (windowExists(s, nums, start)) {
            return start;
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
