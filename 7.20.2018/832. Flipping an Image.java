class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        if (A == null || A.length == 0) {
            return A;
        }
        for (int[] nums : A) {
            flipInvert(nums);
        }
        return A;
    }
    private void flipInvert(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            if (nums[i] == 0 && nums[j] == 0) {
                nums[i++] = 1;
                nums[j--] = 1;
            }
            else if (nums[i] == 1 && nums[j] == 1) {
                nums[i++] = 0;
                nums[j--] = 0;
            }
            else {
                i++;
                j--;
            }
        }
    }
}
