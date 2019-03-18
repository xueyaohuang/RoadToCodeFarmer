class Solution {
    public int splitArray(int[] nums, int m) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int start = (int)(sum / m);
        int end = Integer.MAX_VALUE;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (canSplit(nums, m, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
    
    private boolean canSplit(int[] nums, int m, int sum) {
        int left = 0;
        int count = 0;
        for (int num : nums) {
            if (num > sum) {
                return false;
            }
            if (num > left) {
                count++;
                left = sum;
            }
            left -= num;
        }
        return count <= m;
    }
}
