class Solution {
    // merge sort不是inplace，多益需要辅助array
    int[] temp;
    public int reversePairs(int[] nums) {
        int len = nums.length;
        temp = new int[len];
        return mergeCount(nums, 0, len - 1);
    }
    
    private int mergeCount(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }
        int mid = (start + end) / 2;
        // mergeCount(int[] nums, int start, int end)之后，nums在start和end之间是sorted
        int count = mergeCount(nums, start, mid) + mergeCount(nums, mid + 1, end);
        int i = start, j = mid + 1;
        while (i <= mid) {
            // nums[i] / 2.0 > nums[j] 而不是 nums[i] > 2 * nums[j]为了对付integer overflow的情况
            while (j <= end && nums[i] / 2.0 > nums[j]) {
                j++;
            }
            count += j - mid - 1;
            i++;
        }
        merge(nums, start, end, mid);
        return count;
    }
    
    // 正常的merge
    private void merge(int[] nums, int start, int end, int mid) {
        for (int i = start; i <= end; i++) {
            temp[i] = nums[i];
        }
        int i = start, j = mid + 1, k = start;
        while (i <= mid && j <= end) {
            if (temp[i] <= temp[j]) {
                nums[k++] = temp[i++];
            } else {
                nums[k++] = temp[j++];
            }
        }
        while (i <= mid) {
            nums[k++] = temp[i++];
        }
        while (j <= end) {
            nums[k++] = temp[j++];
        }
    }
}
