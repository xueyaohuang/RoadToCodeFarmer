class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int[] pre = new int[len];
        Arrays.sort(nums);
        int maxLen = 0;
        int end = -1;
        
        for (int i = 0; i < len; i++) {
            pre[i] = -1;
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && dp[i] < 1 + dp[j]) {
                    dp[i] = 1 + dp[j];
                    pre[i] = j;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                end = i;
            }
        }

        List<Integer> res = new ArrayList<>();
        while (end != -1) {
            res.add(nums[end]);
            end = pre[end];
        }
        return res;
    }
}
