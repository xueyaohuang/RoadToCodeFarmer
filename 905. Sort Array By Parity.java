class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int[] res = new int[nums.length];
        int i = 0, j = nums.length - 1;
        for (int num : nums) {
            if (num % 2 == 0) {
                res[i++] = num;
            } else {
                res[j--] = num;
            }
        }
        return res;
    }
}

class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int j = 0;
        for (int i =  0;  i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                swap(nums, i, j++);
            }
        }
        return nums;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
