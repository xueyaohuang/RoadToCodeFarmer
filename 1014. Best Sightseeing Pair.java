/*
It's similar to Best Time to Buy and Sell Stock, but instead of min price, we track max value,
and our max value decays every step due to the distance penalty.

Solution

    Track the maximum value of values[i] as max
    Every turn, decrement max to account for j - i.
    Track and return the maximum score.

*/
class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int max = values[0] - 1;
        int res = 0;
        for (int i = 1; i < values.length; i++) {
            res = Math.max(res, values[i] + max);
            max = Math.max(max, values[i]) - 1;
        }
        return res;
    }
}
