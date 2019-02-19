class Solution {
    public int fib(int N) {
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        int res = 1;
        int twoSteps = 0;
        int oneStep = 1;
        for (int i = 2; i <= N; i++) {
            res = twoSteps + oneStep;
            twoSteps = oneStep;
            oneStep = res;
        }
        return res;
    }
}
