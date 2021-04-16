## method1
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}

## method2
class Solution {
    public int maxProfit(int[] prices) {
        int prevSell = 0;
        int prevBuy = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            // prevSell:上一步卖了，这一步什么也不干。prevBuy + prices[i]:上一步买了，这一步卖。
            int curSell = Math.max(prevSell, prevBuy + prices[i]);
            // prevBuy: 上一步买了，这一步什么也不干。prevSell - prices[i]: 上一步卖了，这一步买。
            int curBuy = Math.max(prevBuy, prevSell - prices[i]);
            prevSell = curSell;
            prevBuy = curBuy;
        }
        return prevSell;
    }
}
