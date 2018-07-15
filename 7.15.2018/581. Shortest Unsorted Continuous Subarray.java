class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        int[] copy = Arrays.copyOf(nums, nums.length);
        // for (int i = 0; i < nums.length; i++) {
        //     copy[i] = nums[i];
        // }
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

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int l = 0;
        int r = len - 1;
        // 先找出开头和结尾递增的两段
        while (l < len - 1 && nums[l] <= nums[l + 1]) {
            l++;
        }
        while (r > 0 && nums[r] >= nums[r - 1]) {
            r--;
        }
        // 找出中间一段的最大和最小值
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = l; i <= r; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        // 保证l要小于min,r大于max
        while (l >= 0 && nums[l] > min) {
            l--;
        }
        while (r < len && nums[r] < max) {
            r++;
        }
        return r > l ? r - l - 1 : 0;
    }
}
