class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int cash = 0;
        int hold = -prices[0];
        int preCash = 0; // 第i天的前2天cash的最大收益
        for (int i = 1; i < prices.length; i++) {
            int temp = cash; // 第i天的前1天cash的最大收益
            cash = Math.max(cash, hold + prices[i]);
            hold = Math.max(hold, preCash - prices[i]);
            preCash = temp;
        }
        return cash;
    }
}
