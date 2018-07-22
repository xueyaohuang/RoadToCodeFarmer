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
        return partitionHelper(nums, 0, k, sum / k, visited, 0);
    }
    
    private boolean partitionHelper(int[] nums, int start, int k, int target, boolean[] visited, int sum) {
        if (k == 1) {
            return true;
        }
        for (int i = start; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sum += nums[i];
                if (sum == target) {
                    if (partitionHelper(nums, 0, k - 1, target, visited, 0)) {
                        return true;
                    }
                }
                else if (sum < target) {
                    if (partitionHelper(nums, i + 1, k, target, visited, sum)) {
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
