class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        int sum = 0;
        int cur = 0;
        for (int i = 2; i < A.length; i++) {
            if (2 * A[i - 1] == A[i] + A[i - 2]) {
                cur++;
                sum += cur;
            } else {
                cur = 0;
            }
        }
        return sum;
    }
}
