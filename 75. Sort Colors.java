// Dutch national flag problem
// threeway partition
// 把1放中间，0往左边扔，2往右边扔
class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int len = nums.length;
        int idx0 = 0, idx1 = 0, idx2 = len - 1;
        
        while (idx1 <= idx2) {
            if (nums[idx1] == 0) {
                swap(nums, idx0, idx1);
                idx0++;
                idx1++;
            } else if (nums[idx1] == 1) {
                idx1++;
            } else {
                swap(nums, idx1, idx2);
                idx2--;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// 2在最后，所以要多移动num of 0 和 num of 1 个距离。
class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int idx0 = 0, idx1 = 0, idx2 = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                nums[idx2++] = 2;
                nums[idx1++] = 1;
                nums[idx0++] = 0;
            } else if (nums[i] == 1) {
                nums[idx2++] = 2;
                nums[idx1++] = 1;
            } else {
                nums[idx2++] = 2;
            }
        }
    }
}
