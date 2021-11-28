// dp
// O(n)
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int len = days.length;
        int[] dp = new int[days[len - 1] + 1];
        int dayIdx = 0;
        for (int i = 1; i < dp.length; i++) {
            if (i == days[dayIdx]) {
                dp[i] = minOfThree(dp[Math.max(0, i - 1)] + costs[0],
                                   dp[Math.max(0, i - 7)] + costs[1],
                                   dp[Math.max(0, i - 30)] + costs[2]);
                dayIdx++;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[dp.length - 1];
    }
    
    private int minOfThree(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}

// dp
// O(n^2)
// or O(29n), since the inner for loop will never beyond 29
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[days.length + 1];
        int minOneDayCost = Math.min(Math.min(costs[0], costs[1]), costs[2]);
        dp[0] = 0;
        dp[1] = minOneDayCost;
        for (int i = 1; i <= days.length; i++) {
            dp[i] = dp[i - 1] + minOneDayCost;
            for (int j = i - 2; j >= 0; j--) {
                if (days[i - 1] - days[j] + 1 <= 7) {
                    dp[i] = Math.min(dp[i], dp[j] + costs[1]);
                }
                if (days[i - 1] - days[j] + 1 <= 30) {
                    dp[i] = Math.min(dp[i], dp[j] + costs[2]);
                } 
                if (days[i - 1] - days[j] + 1 > 30) {
                    break;
                }
            }
        }
        return dp[dp.length - 1];
    }
}
