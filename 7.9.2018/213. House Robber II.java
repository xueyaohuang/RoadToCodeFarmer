class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(robHelper(nums, 0, nums.length - 2), robHelper(nums, 1, nums.length - 1));
    }
    private int robHelper(int[] nums, int start, int end) {
        int rob = 0;
        int notRob = 0;
        for (int i = start; i <= end; i++) {
            int robCur = notRob + nums[i];
            int notRobCur = Math.max(rob, notRob);
            rob = robCur;
            notRob = notRobCur;
        }
        return Math.max(rob, notRob);
    }
}
