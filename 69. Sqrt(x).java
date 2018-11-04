class Solution {
    public int mySqrt(int x) {
        long res = x;
        while (res * res > x) {
            res = (res + x / res) / 2;
        }
        return (int)res;
    }
}

class Solution {
    public int mySqrt(int x) {
        int start = 0, end = x;
        while (start < end) {
            int mid = (start + end + 1) / 2;
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
}
