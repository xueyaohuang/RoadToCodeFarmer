class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}

class Solution {
    public int numSquares(int n) {
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        boolean[] visited = new boolean[n];
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int j = 1; j * j <= cur; j++) {
                    if (j * j == cur) {
                        return count;
                    }
                    int remain = cur - j * j;
                    if (!visited[remain]) {
                        queue.offer(remain);
                        visited[remain] = true;
                    }
                }
            }
        }
        return n;
    }
}
