class Solution {
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        int left = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (left == sum - nums[i] - left) {
                return i;
            }
            left += nums[i];
        }
        return -1;
    }
}

class Solution {
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int len = nums.length;
        int[] forwardSum = new int[len];
        int[] backwardSum = new int[len];
        forwardSum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            forwardSum[i] = forwardSum[i - 1] + nums[i];
        }
        backwardSum[len - 1] = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            backwardSum[i] = backwardSum[i + 1] + nums[i];
        }
        for (int i = 0; i < len; i++) {
            if (forwardSum[i] == backwardSum[i]) {
                return i;
            }
        }
        return -1;
    }
}
