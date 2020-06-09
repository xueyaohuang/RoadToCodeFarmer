// binary search
// invariant: 每次iteration后，nums内部一定有至少一个数比[start]和nums[end]大
// 跟mid+1比
class Solution {
    public int findPeakElement(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] > nums[mid + 1]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}

// 跟mid-1比
class Solution {
    public int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end + 1) / 2;
            if (nums[mid] > nums[mid - 1]) {
                start = mid; // start 若等于mid+1，则可能超出范围
            }
            else {
                end = mid - 1; 
            }
        }
        return start;
    }
}
