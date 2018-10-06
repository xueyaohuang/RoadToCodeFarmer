class Solution {
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int len = nums.length;
        int count = 0;
        for (int i = len - 1; i >= 2; i--) {
            int j = 0;
            int k = i - 1;
            while (j < k) {
                if (nums[j] + nums[k] <= nums[i]) {
                    j++;
                } else {
                    count += k - j;
                    k--;
                }
            }
        }
        return count;
    }
}
