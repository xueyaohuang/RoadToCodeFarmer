/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        // find peak
        int peakIdx = findPeakIndex(mountainArr);
        if (mountainArr.get(peakIdx) == target) {
            return peakIdx;
        }
        // find in left
        int n = mountainArr.length();
        int res = findInAsendingSubarray(target, mountainArr, 0, peakIdx - 1);
        if (res != -1) {
            return res;
        }
        // find in right
        res = findInDesendingSubarray(target, mountainArr, peakIdx + 1, n - 1);
        if (res != -1) {
            return res;
        }
        return -1;
    }
    
    private int findPeakIndex(MountainArray mountainArr) {
        int start = 0, end = mountainArr.length() - 1;
        while (start < end) {
            int mid = (start + end + 1) / 2;
            if (mountainArr.get(mid) > mountainArr.get(mid - 1)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
    
    private int findInAsendingSubarray(int target, MountainArray mountainArr, int left, int right) {
        int start = left, end = right;
        while (start < end) {
            int mid = (start + end) / 2;
            if (mountainArr.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return mountainArr.get(start) == target ? start : -1;
    }
    
    private int findInDesendingSubarray(int target, MountainArray mountainArr, int left, int right) {
        int start = left, end = right;
        while (start < end) {
            int mid = (start + end) / 2;
            if (mountainArr.get(mid) > target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return mountainArr.get(start) == target ? start : -1;
    }
}
