class Solution {
    public int findMin(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i +1]) {
                return nums[i + 1];
            }
        }
        return nums[0];
    }
}

class Solution {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[end] < nums[mid]) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (nums[start] > nums[end]) {
            return nums[end];
        }
        return nums[start];
    }
}
// must use if (nums[end] < nums[mid]) not compare nums[start] to nums[end], such that we can cover the case that there is no rotation.

