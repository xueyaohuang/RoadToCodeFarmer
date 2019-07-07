class Solution {
    public int[] sortedSquares(int[] A) {
        int[] squares = new int[A.length];
        int leftIdx = 0;
        int rightIdx = A.length - 1;
        int squaresIdx = A.length - 1;
        
        while (leftIdx <= rightIdx) {
            if ((A[leftIdx] * A[leftIdx]) >= A[rightIdx] * A[rightIdx]) {
                squares[squaresIdx] = A[leftIdx] * A[leftIdx];
                leftIdx++;
            } else {
                squares[squaresIdx] = A[rightIdx] * A[rightIdx];
                rightIdx--;
            }
            squaresIdx--;
        }
        return squares;
    }
}

class Solution {
    public int[] sortedSquares(int[] A) {
        if (A == null || A.length == 0) {
            return new int[0];
        }
        int len = A.length;
        int mid = 0;
        int diff = Math.abs(A[0]);
        int[] res = new int[len];
        for (int i = 1; i < len; i++) {
            if (Math.abs(A[i]) < diff) {
                mid = i;
                diff = Math.abs(A[i]);
            }
            if (i > 0 && A[i - 1] >= 0 && A[i] >= 0) {
                break;
            }
        }
        int left = mid - 1;
        int right = mid + 1;
        res[0] = A[mid] * A[mid];
        int idx = 1;
        while (left >= 0 && right < len) {
            if (Math.abs(A[left]) > Math.abs(A[right])) {
                res[idx++] = A[right] * A[right];
                right++;
            } else {
                res[idx++] = A[left] * A[left];
                left--;
            }
        }
        while (left >= 0) {
            res[idx++] = A[left] * A[left];
            left--;
        }
        while (right < len) {
            res[idx++] = A[right] * A[right];
            right++;
        }
        return res;
    }
}
