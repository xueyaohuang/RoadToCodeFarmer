class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        int cur = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                cur++;
            } else {
                res = Math.max(res, cur);
                cur = 1;
            }
        }
        return Math.max(res, cur);
    }
}
