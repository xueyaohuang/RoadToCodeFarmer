// dp
// O(n)
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[days[n - 1] + 1];
        int idx = 0;
        for (int i = 1; i <= days[n - 1]; i++) {
            if (i == days[idx]) {
                int oneDay = dp[i - 1] + costs[0];
                int sevenDays = dp[Math.max(0, i - 7)] + costs[1];
                int thirtyDays = dp[Math.max(0, i - 30)] + costs[2];
                dp[i] = Math.min(Math.min(oneDay, sevenDays), thirtyDays);
                idx++;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[days[n - 1]];
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
