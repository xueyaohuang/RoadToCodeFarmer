// 这种尝试所有可能组合的题，基本上就是backtracking
/*
To tell whether there are exactly k subsets with equal subset sum sum/k, we may start from
1. each subset: for each subset, put any numbers inside
2. each number: for each number, put it into any subset

*/
// 1.
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = getSum(nums);
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        int[] subsetSum = new int[k];
        for (int i = 0; i < k; i++) {
            subsetSum[i] = sum / k;
        }
        return backtracking(nums, subsetSum, nums.length - 1, k, sum / k);
    }
    
    private int getSum(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res += n;
        }
        return res;
    }
    
    private boolean backtracking(int[] nums, int[] subsetSum, int idx, int k, int target) {
        if (idx == -1) {
            return true;
        }
        for (int i = 0; i < k; i++) {
            if (subsetSum[i] >= nums[idx]) {
                subsetSum[i] -= nums[idx];
                if (backtracking(nums, subsetSum, idx - 1, k, target)) {
                    return true;
                }
                subsetSum[i] += nums[idx];
            }
            if (subsetSum[i] == target) {
                return false;
            }
        }
        return false;
    }
}

// 2.
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = getSum(nums);
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        return backtracking(nums, k, sum / k, 0, used, nums.length - 1);
    }
    
    private int getSum(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res += n;
        }
        return res;
    }
    
    private boolean backtracking(int[] nums, int k, int target, int sum, boolean[] used, int start) {
        if (k == 1) {
            return true;
        }
        if (sum == target) {
            return backtracking(nums, k - 1, target, 0, used, nums.length - 1);
        }
        for (int i = start; i >= 0; i--) {
            if (used[i] || sum + nums[i] > target) {
                continue;
            }
            sum += nums[i];
            used[i] = true;
            if (backtracking(nums, k, target, sum, used, i - 1)) {
                return true;
            }
            sum -= nums[i];
            used[i] = false;
        }
        return false;
    }
}

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = getSum(nums);
        if (sum % k != 0) {
            return false;
        }
        boolean[] used = new boolean[nums.length];
        return backtracking(nums, k, sum / k, 0, used, 0);
    }
    
    private int getSum(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res += n;
        }
        return res;
    }
    
    private boolean backtracking(int[] nums, int k, int target, int sum, boolean[] used, int start) {
        // 由于已经检查了sum可以被k整除，所以只剩k=1时，剩下的数之和一定等于sum/k
        // 也可以检查 if(k == 0)v
        if (k == 1) {
            return true;
        }
        if (sum == target) {
            // 找好一个sum，继续找，直到找完k个，这里的start又重0开始了，因为重新找一个组合。
            return backtracking(nums, k - 1, target, 0, used, 0);
        }
        for (int i = start; i < nums.length; i++) {
            if (used[i] || sum + nums[i] > target) {
                continue;
            }
            sum += nums[i];
            used[i] = true;
            if (backtracking(nums, k, target, sum, used, i + 1)) {
                return true;
            }
            sum -= nums[i];
            used[i] = false;
        }
        return false;
    }
}

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
        // 由于已经检查了sum可以被k整除，所以只剩k=1时，剩下的数之和一定等于sum/k
        // 也可以检查 if(k == 0)
        if (k == 1) {
            return true;
        }
        for (int i = start; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sum += nums[i];
                // 找好一个sum，继续找，直到找完k个，这里的start又重0开始了，因为重新找一个组合。
                if (sum == target) {
                    if (partitionHelper(nums, 0, k - 1, target, visited, 0)) {
                        return true;
                    }
                }
                // sum < target还可以继续完成当前sum。若sum > target，就要返回上层recursion。
                else if (sum < target) {
                    if (partitionHelper(nums, i + 1, k, target, visited, sum)) {
                        return true;
                    }
                }
                // 返回上层recursion，把当前加入的num[i]去掉
                visited[i] = false;
                sum -= nums[i];
            }
        }
        return false;
    }
}
