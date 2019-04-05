// super slow, brute force
class Solution {
    public int longestMountain(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        int len = A.length;
        int res = 0;
        for (int i = 1; i < len - 1; i++) {
            res = Math.max(res, extendMountain(A, i));
        }
        return res;
    }
    
    private int extendMountain(int[] A, int peak) {
        int i = peak, j = peak;
        while (i > 0 && A[i] > A[i - 1]) {
            i--;
        }
        while (j < A.length - 1 && A[j] > A[j + 1]) {
            j++;
        }
        if (i == peak || j == peak || j - i + 1 < 3) {
            return 0;
        }
        return j - i + 1;
    }
}
