// Since every house is either robbed or not robbed and at least half of the houses are not robbed,
// the solution is simply the larger of two cases with consecutive houses, i.e. house i not robbed,
// break the circle, solve it, or house i + 1 not robbed. Hence, the following solution.
// I chose i = n and i + 1 = 0 for simpler coding. But, you can choose whichever two consecutive ones.

// 1. need to break the circle
// 2. can break it at any point, and run the rob helper
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
    // This is House Robber I
    private int robHelper(int[] nums, int start, int end) {
        int rob = 0;
        int notRob = 0;
        for (int i = start; i <= end; i++) {
            int temp = rob;
            rob = notRob + nums[i];
            notRob = Math.max(temp, notRob);
        }
        return Math.max(rob, notRob);
    }
}
