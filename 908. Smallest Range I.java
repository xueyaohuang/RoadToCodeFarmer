class Solution {
    public int smallestRangeI(int[] A, int K) {
        int max = A[0];
        int min = A[0];
        for (int i = 1; i < A.length; i++) {
            max = Math.max(max, A[i]);
            min = Math.min(min, A[i]);
        }
        return Math.max(max - min - 2 * K, 0);
    }
}
