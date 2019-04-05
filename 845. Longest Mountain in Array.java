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

class Solution {
    public int longestMountain(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        int len = A.length;
        int[] forward = new int[len];
        int[] backward = new int[len];
        for (int i = 1; i < len; i++) {
            if (A[i] > A[i - 1]) {
                forward[i] = forward[i - 1] + 1;
            } else {
                forward[i] = 0;
            }
        }
        for (int i = len - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                backward[i] = backward[i + 1] + 1;
            } else {
                backward[i] = 0;
            }
        }
        int res = 0;
        for (int i = 1; i < len - 1; i++) {
            if (forward[i] == 0 || backward[i] == 0) {
                continue;
            }
            if (forward[i] + backward[i] + 1 >= 3 && forward[i] + backward[i] + 1 > res) {
                res = forward[i] + backward[i] + 1;
            }
        }
        return res;
    }
}

class Solution {
    public int longestMountain(int[] A) {
        int len = A.length;
        int start = 0;
        int end = 0;
        int res = 0;
        while (start < len - 2) {
            while (start < len - 1 && A[start] >= A[start + 1]) {
                start++;
            }
            end = start + 1;
            while (end < len - 1 && A[end] < A[end + 1]) {
                end++;
            }
            while (end < len - 1 && A[end] > A[end + 1]) {
                end++;
                res = Math.max(res, end - start + 1);
            }
            start = end;
        }
        return res;
    }
}
