// https://leetcode.com/problems/best-meeting-point/discuss/74209/strict-mathematics-proof-to-minimize-sum_i-absx-x_i-using-triangle-inequality
class Solution {
    public int minMoves2(int[] nums) {
        int res = 0;
        int len = nums.length;
        int median = randomQuickSelect(nums, 0, len - 1, len / 2);
        
        for (int i = 0; i < len; i++) {
            res += Math.abs(nums[i] - median);
        }
        
        return res;
    }
    
    private int randomQuickSelect(int[] nums, int start, int end, int k) {
        Random rand = new Random();
        int pivotIdx = rand.nextInt(end - start + 1) + start;
        int pivot = nums[pivotIdx];
        swap(nums, pivotIdx, end);
        int left = start;
        
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                swap(nums, left, i);
                left++;
            }
        }
        
        swap(nums, left, end);
        
        if (left == k) {
            return nums[k];
        } else if (left < k) {
            return randomQuickSelect(nums, left + 1, end, k);
        } else {
            return randomQuickSelect(nums, start, left - 1, k);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
