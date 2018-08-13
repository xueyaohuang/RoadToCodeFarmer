class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int rob = 0;
        int notRob = 0;
        for (int i = 0; i < nums.length; i++) {
            int preRob = rob;
            rob = Math.max(preRob, notRob + nums[i]); //if rob current value, previous house must not be robbed
            notRob = Math.max(preRob, notRob) //if not rob ith house, take the max value of robbed (i-1)th house and not rob (i-1)th house
        }
        return Math.max(rob, notRob);
    }
}
