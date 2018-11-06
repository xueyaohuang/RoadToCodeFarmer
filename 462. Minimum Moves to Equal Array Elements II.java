// https://leetcode.com/problems/best-meeting-point/discuss/74209/strict-mathematics-proof-to-minimize-sum_i-absx-x_i-using-triangle-inequality
class Solution {
    public int minMoves2(int[] nums) {
        int sum = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length / 2; i++) {
            sum += nums[nums.length - 1 - i] - nums[i];
        }
        return sum;
    }
}
