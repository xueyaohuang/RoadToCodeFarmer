class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int cash = 0;
        int hold = -prices[0];
        int pre_cash = 0;
        for (int i = 1; i < prices.length; i++) {
            int temp = cash;
            cash = Math.max(cash, hold + prices[i]);
            hold = Math.max(hold, pre_cash - prices[i]);
            pre_cash = temp;
        }
        return cash;
    }
}
