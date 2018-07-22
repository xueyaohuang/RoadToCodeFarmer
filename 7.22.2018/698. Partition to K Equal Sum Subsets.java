class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || k <= 0 || nums.length < k) {
            return false;
        }
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % k != 0) {
            return false;
        }
        boolean[] visited = new boolean[nums.length];
        return helper(nums, 0, k, sum / k, visited, 0);
    }
    
    private boolean helper(int[] nums, int pos, int k, int target, boolean[] visited, int sum) {
        if (k == 1) {
            return true;
        }
        for (int i = pos; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sum += nums[i];
                if (sum == target) {
                    if (helper(nums, 0, k - 1, target, visited, 0)) {
                        return true;
                    }
                } else if (sum < target) {
                    if (helper(nums, i + 1, k, target, visited, sum)) {
                        return true;
                    }
                }
                visited[i] = false;
                sum -= nums[i];
            }
        }
        return false;
    }
}
