class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        // Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                } else {
                    return a[0] - b[0];
                }
            }
        });
        int len = envelopes.length;
        int[] dp = new int[len];
        int res = 0;
        for (int[] env : envelopes) {
            int i = Arrays.binarySearch(dp, 0, res, env[1]);
            if (i < 0) {
                i = -i - 1;
                dp[i] = env[1];
            }
            if (i == res) {
                res++;
            }
        }
        return res;
    }
}
