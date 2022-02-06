// naive O(n^2)
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double res = Integer.MIN_VALUE;
        // i is the start index of subarray and j is end index of subarray
        for (int i = 0; i + k <= nums.length; i++) {
            long sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (j - i + 1 >= k) {
                    res = Math.max(res, sum * 1.0 / (j - i + 1));
                }
            }
        }
        return res;
    }
}

/*
Why do we use Binary Search ?
'The answer with the calculation error less than 10-5 will be accepted.' Binary Search is good when we want to guess the solution and shrink the range to get precise value.
How do we use Binary Search ?
l = minimum result possible, -10001 initially
r = maximum result possible, 10001 initially
we pick the middle value and see if we can find a larger result to do the standard Binary Search.
canFindLargerAverage() helps us check that :
(a1+a2+a3...+aj)/j ≥ mid <=  (a1−mid)+(a2−mid)+(a3−mid)...+(aj−mid) ≥ 0
a[] stores a1-mid, a2-mid, a3-mid ... aj-mid
Similar to Sliding Window technique, for first k elements, we get the cumulative sum among them, cur. If it is smaller than 0, we slide the window.
Attention that, prev keeps track of cumulative sum in front of the current window. If prev is smaller than 0, the cumulative sum of current window,
which equals to cur - prev, becomes bigger, i.e., is more possible to >= 0.
*/

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double start = Integer.MAX_VALUE, end = Integer.MIN_VALUE;
        for (int n : nums) {
            start = Math.min(start, n);
            end = Math.max(end, n);
        }
        while (start + 0.00001 < end) {
            double mid = (start + end) / 2;
            if (canHaveAverage(nums, k, mid)) {
                start = mid + 0.000001;
            } else {
                end = mid - 0.000001;
            }
        }
        return start;
    }   
    
    private boolean canHaveAverage(int[] nums, int k, double x) {
        int n = nums.length;
        double[] a = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = nums[i] - x;
        }
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += a[i];
        }
        if (sum >= 0) {
            return true;
        }
        double prevWindowSum = 0;
        for (int i = k; i < n; i++) {
            sum += a[i];
            prevWindowSum += a[i - k];
            if (prevWindowSum < 0) {
                sum -= prevWindowSum;
                prevWindowSum = 0;
            }
            if (sum >= 0) {
                return true;
            }
        }
        return false;
    }
}
