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

class Solution {
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[len - 1] > nums[len - 2]) {
            return len - 1;
        }
        for (int i = 1; i < len - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return 0;
    }
}

class Solution {
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (isPeak(nums, i)) {
                return i;
            }
        }
        return -1;
    }
    
    private boolean isPeak(int[] nums, int i) {
        return (i == 0 || nums[i] > nums[i - 1]) && (i == nums.length - 1 || nums[i] > nums[i + 1]);
    }
}
