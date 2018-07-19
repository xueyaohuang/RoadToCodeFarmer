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
