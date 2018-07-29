class Solution {
    public int[][] transpose(int[][] A) {
        if (A == null || A.length == 0) {
            return new int[0][0];
        }
        int m = A.length;
        int n = A[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = A[j][i];
            }
        }
        return res;
    }
}
