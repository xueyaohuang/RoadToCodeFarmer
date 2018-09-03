class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int maxProfit = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return maxProfit;
    }
}

// Kadane's Algorithm
class Solution {
    public int maxProfit(int[] prices) {
        int maxCur = 0;
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            maxCur = maxCur > 0 ? maxCur + prices[i] - prices[i - 1] : prices[i] - prices[i - 1];
            max = Math.max(max, maxCur);
        }
        return max;
    }
}
