class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 1;
        }
        for (int i = 0; i < nums.length; i++) {
            // 这里必须用while，因为可能当前不对的换走了，新来的还是不对
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }       
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
