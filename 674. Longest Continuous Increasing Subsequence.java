class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int res = 1;
        int curMax = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                curMax++;
            } else {
                res = Math.max(res, curMax);
                curMax = 1;
            }
        }
        return Math.max(res, curMax);
    }
}
