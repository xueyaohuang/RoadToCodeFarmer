// Binary search, O(nlogk), k is the max value in arr
class Solution {
    public int findBestValue(int[] arr, int target) {
        int start = 0, end = getMax(arr);
        int diff = Integer.MAX_VALUE, res = 0;
        while (start < end) {
            int mid = (start + end + 1) / 2;
            int curDiff = getSum(arr, mid) - target;
            if (Math.abs(curDiff) < Math.abs(diff)) {
                diff = curDiff;
                res = mid;
            } else if (Math.abs(curDiff) == Math.abs(diff)) {
                res = Math.min(res, mid);
            }
            if (curDiff > 0) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }
        // 通常情况的binary search都是直接return start，但是这里需要比较start和res哪个好
        if (Math.abs(getSum(arr, start) - target) <= Math.abs(diff)) {
            return start;
        }
        return res;
    }
    
    private int getMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int a : arr) {
            max = Math.max(max, a);
        }
        return max;
    }
    
    private int getSum(int[] arr, int value) {
        int sum = 0;
        for (int a : arr) {
            sum += a > value ? value : a;
        }
        return sum;
    }
}
