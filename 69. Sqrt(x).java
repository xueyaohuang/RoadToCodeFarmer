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
        int start = 0;
        int end = x;
        while (start < end) {
            int mid = (end + start + 1) / 2;
            if (mid == x / mid) {
                return mid;
            }
            else if (mid > x / mid) {
                end = mid - 1;
            }
            else {
                start = mid;
            }
        }
        return start;
    }
}
