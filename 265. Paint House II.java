//  1. O(nk^2)
class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int[] minIdx = minIndex(costs[i - 1]);
                if (j == minIdx[0]) {
                    costs[i][j] += costs[i - 1][minIdx[1]];
                } else {
                    costs[i][j] += costs[i - 1][minIdx[0]];
                }
            }
        }
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            minCost = Math.min(minCost, costs[n - 1][i]);
        }
        return minCost;
    }
    
    private int[] minIndex(int[] nums) {
        int min1 = Math.min(nums[0], nums[1]);
        int min2 = Math.max(nums[0], nums[1]);
        int idx1 = nums[0] < nums[1] ? 0 : 1;
        int idx2 = nums[0] < nums[1] ? 1 : 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] < min1) {
                min2 = min1;
                idx2 = idx1;
                min1 = nums[i];
                idx1 = i;
            } else if (nums[i] < min2) {
                min2 = nums[i];
                idx2 = i;
            }
        }
        return new int[]{idx1, idx2};
    }
}

// O(nk)
class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;
        int min1 = 0;
        int min2 = 0;
        int minIdx = -1;
        for (int i = 0; i < n; i++) {
            int curMin1 = Integer.MAX_VALUE;
            int curMin2 = Integer.MAX_VALUE;
            int curMinIdx = 0;
            for (int j = 0; j < k; j++) {
                int cost = costs[i][j] + (j == minIdx ? min2 : min1);
                if (cost < curMin1) {
                    curMin2 = curMin1;
                    curMin1 = cost;
                    curMinIdx = j;
                } else if (cost < curMin2) {
                    curMin2 = cost;
                }
            }
            min1 = curMin1;
            min2 = curMin2;
            minIdx = curMinIdx;
        }
        return min1;
    }
}
