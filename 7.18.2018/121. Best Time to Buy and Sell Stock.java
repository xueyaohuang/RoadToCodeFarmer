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
