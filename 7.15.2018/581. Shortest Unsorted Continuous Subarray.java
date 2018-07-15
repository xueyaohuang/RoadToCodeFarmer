class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        int[] copy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copy[i] = nums[i];
        }
        Arrays.sort(copy);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == copy[i]) {
                start++;
            } else {
                break;
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == copy[i]) {
                end--;
            } else {
                break;
            }
        }
        return Math.max(0, end - start + 1);
    }
}
