class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= first) {  // 先更新最小的，这样保证最小的一定在第二小的之前
                first = nums[i];
            }
            else if (nums[i] <= second) {  // 再更新第二小的，这样保证第二小的一定在第三之前
                second = nums[i];
            }
            else {  // 跳过了前两个，这个比前两个大，又能保证顺序，返回true
                return true;
            }
        }
        return false;
    }
}
