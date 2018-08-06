// 2在最后，所以要多移动num of 0 和 num of 1 个距离。
class Solution {
    public void sortColors(int[] nums) {
        int len = nums.length;
        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                nums[++n2] = 2;
                nums[++n1] = 1;
                nums[++n0] = 0;
            }
            else if (nums[i] == 1) {
                nums[++n2] = 2;
                nums[++n1] = 1;
            }
            else {
                nums[++n2] = 2;
            }
        }
    }
}
