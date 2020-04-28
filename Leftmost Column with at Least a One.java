/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

// O(nlgm)
class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int m = dimensions.get(0);
        int n = dimensions.get(1);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            int col = getLeftMostOne(i, n, binaryMatrix);
            if (col != -1) {
                res = Math.min(res, col);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    private int getLeftMostOne(int i, int col, BinaryMatrix binaryMatrix) {
        int start = 0;
        int end = col - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (binaryMatrix.get(i, mid) == 1) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return binaryMatrix.get(i, start) == 1 ? start : -1;
    }
}

// O(n + m)
class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int m = dimensions.get(0);
        int n = dimensions.get(1);
        int res = -1;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (binaryMatrix.get(i, j) == 0) {
                i++;
            } else {
                res = j;
                j--;
            }
        }
        return res;
    }
}
