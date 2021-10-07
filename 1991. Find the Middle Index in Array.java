class Solution {
    public int findMiddleIndex(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            sum -= nums[i];
            if (cur == sum) {
                return i;
            }
            cur += nums[i];
        }
        return -1;
    }
}
