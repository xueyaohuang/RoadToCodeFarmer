class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int index1 = -1;
        int index2 = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i- 1]) {
                index1 = i - 1;
                break;
            }        
        }
        if (index1 == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        for (int i = nums.length - 1; i > index1; i--) {
            if (nums[i] > nums[index1]) {
                index2 = i;
                break;
            }
        }
        swap(nums, index1, index2);
        reverse(nums, index1 + 1, nums.length - 1);
        
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;        
    }
    
    private void reverse(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        for (int i = start; i <= (start + end) / 2; i++) {
            swap(nums, i, start + end - i);
        }
    }
}

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int idx1 = nums.length - 1;
        while (idx1 > 0 && nums[idx1 - 1] >= nums[idx1]) { 
            idx1--;   
        }
        if (idx1 == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        idx1--;
        int idx2 = nums.length - 1;
        while (idx2 > idx1 && nums[idx2] <= nums[idx1]) {
            idx2--;
        }
        swap(nums, idx1, idx2);
        reverse(nums, idx1 + 1, nums.length - 1);  
    }
    
    private void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
