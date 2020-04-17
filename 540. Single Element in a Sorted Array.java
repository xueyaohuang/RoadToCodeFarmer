class Solution {
    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid % 2 == 0) {
                if (nums[mid] < nums[mid + 1]) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            } else {
                if (nums[mid] < nums[mid + 1]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
        }
        return nums[start];
    }
}

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1]) {
                return mid;
            }
            if (mid % 2 == 0) {
                if (nums[mid] == nums[mid - 1]) {
                    end = mid - 1;
                } else {
                    start = mid; // 不能加，比如[3,3,7,7,10,11,11]，mid=4，此时start不能等于mid+1，只能等于mid，若加1，真正要返回的数就出了nums[start]和nums[end]之间的范围了。
                }
            } else {
                if (nums[mid] == nums[mid - 1]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return nums[start];
    }
}
