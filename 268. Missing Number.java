class Solution {
    public int missingNumber(int[] nums) {
        int res = nums.length; // [0, nums.length) missing nums.length, so initialize res to nums.length.
        for (int i = 0; i < nums.length; i++) {
            res = res ^ i ^ nums[i]; // a ^ b ^ b = a
        }
        return res;
    }
}

class Solution {
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        return len * (len + 1) / 2 - sum;
    }
}

