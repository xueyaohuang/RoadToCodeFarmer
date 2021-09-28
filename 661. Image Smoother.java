class Solution {
    public int[][] imageSmoother(int[][] img) {
        int m = img.length, n = img[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = getAverage(img, i, j);
            }
        }
        return res;
    }
    
    private int getAverage(int[][] img, int x, int y) {
        int m = img.length, n = img[0].length;
        int sum = 0, count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < m && j >= 0 && j < n) {
                    sum += img[i][j];
                    count++;
                }
            }
        }
        return sum / count;
    }
}
