class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int res = 1;
        int cur = 1;
        for (int i = 1; i < len; i++) {
            cur = nums[i] > nums[i - 1] ? cur + 1 : 1;
            res = Math.max(cur, res);
        }
        return res;
    }
}
