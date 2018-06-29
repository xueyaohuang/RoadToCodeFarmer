class Solution {
    public int maxProduct(int[] nums) {
        int maxCur = nums[0];
        int minCur = nums[0];
        int maxSofar = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = maxCur;
                maxCur = minCur;
                minCur = temp;
            }
            maxCur = Math.max(nums[i], nums[i] * maxCur);
            minCur = Math.min(nums[i], nums[i] * minCur);
            maxSofar = Math.max(maxSofar, maxCur);
        }
        return maxSofar;
    }
}