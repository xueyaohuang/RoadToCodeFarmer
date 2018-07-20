class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int start = 0;
        int end = A.length - 1;
        int mid = 1;
        while (start < end) {
            mid = start + (end - start) / 2;
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                return mid;
            }
            else if (A[mid] > A[mid - 1]) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        return start;
    }
}
