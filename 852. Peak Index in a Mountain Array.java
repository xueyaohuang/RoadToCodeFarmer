// 此题start可以加1，end也可以减1.
// 看似start不能加1，因为若start刚好在peak，加1就越界了。
// 但是如果start在peak的位置，先检查的是if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1])，会直接返回peak。
class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int start = 0;
        int end = A.length - 1;
        while (start < end) {
            int mid = (start + end + 1) / 2;
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                return mid;
            } else if (A[mid] > A[mid - 1]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
}
