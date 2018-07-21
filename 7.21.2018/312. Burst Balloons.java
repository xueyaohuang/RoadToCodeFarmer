class Solution {
    public int maxCoins(int[] nn) {
    	int n = nn.length;
    	int[] nums = new int[n+2];
    	for(int i=0; i<n; i++) nums[i+1] = nn[i];
    	nums[0] = nums[n+1] = 1;

        int[][] dp = new int[n+2][n+2];
        
        for(int j=2; j < dp.length; j++) {
        	for(int i=0; i+j<dp.length; i++) {
        		find(nums, dp, i, i+j);
        	}
        }

        return dp[0][n+1];
    }
    
    private void find(int[] nums, int[][] dp, final int start, final int end) {
        int r = nums[start]*nums[end];
        for(int k=start+1; k<end; k++) {
            dp[start][end] = Math.max(dp[start][end], dp[start][k] + dp[k][end] + r*nums[k]);
        }
    }
}
