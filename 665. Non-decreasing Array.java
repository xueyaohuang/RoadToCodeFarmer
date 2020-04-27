// This problem is like a greedy problem.
// When you find nums[i] > nums[i + 1] for some i, you will prefer to change nums[i]'s value,
// since a larger nums[i + 1] will give you more risks that you get inversion errors after position i.
// But, if you also find nums[i - 1] > nums[i + 1], then you have to change nums[i + 1]'s value instead
class Solution {
    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length < 3) {
            return true;
        }
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                count++;
                // 发现nums[i] > nums[i + 1] 后，要么nums[i] = nums[i + 1]，要么nums[i + 1] = nums[i]
                // 改成nums[i] = nums[i + 1]更好，因为这样nums[i + 1]没有变大，nums[i + 1]再比nums[i + 2]的
                // 概率就小一些。但是如果nums[i + 1] < nums[i - 1]，就必须改nums[i + 1]。
                if (i > 0 && nums[i + 1] < nums[i - 1]) {
                    nums[i + 1] = nums[i];
                } else {
                    nums[i] = nums[i + 1];
                }
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }
}
