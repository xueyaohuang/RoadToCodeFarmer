class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                continue;
            }
            int j = i;
            int k = next(nums, i);
            while (nums[k] * nums[i] > 0 && nums[next(nums, k)] * nums[i] > 0) {
                if (j == k) {
                    if (next(nums, j) == j) {
                        break;
                    }
                    return true;
                }
                j = next(nums, j);
                k = next(nums, next(nums, k));
            }
            j = i;
            while (nums[j] * nums[i] > 0) {
                nums[j] = 0;
                j = next(nums, j);
            }
        }
        return false;
    }
    private int next(int[] nums, int i) {
        int len = nums.length;
        return i + nums[i] >= 0 ? (i + nums[i]) % len : len + (i + nums[i]) % len;
    }
}
