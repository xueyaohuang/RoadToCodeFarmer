class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxSoFar = 0;
        int maxEndHere = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                maxEndHere++;
                maxSoFar = Math.max(maxSoFar, maxEndHere);
            } else {
                maxEndHere = 0;
            }
        }
        return maxSoFar;
    }
}
