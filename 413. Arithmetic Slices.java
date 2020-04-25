class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        int cur = 0;
        int res = 0;
        for (int i = 2; i < A.length; i++) {
            if (2 * A[i - 1] == A[i] + A[i - 2]) {
                // cur代表以A[i]结尾的等差数列的个数
                cur++;
                res += cur;
            } else {
                cur = 0;
            }
        }
        return res;
    }
}
