// 先用二分法或者牛顿法求出整数平方根start，再判断start * start == num？
class Solution {
    public boolean isPerfectSquare(int num) {
        int start = 1;
        int end = num;
        while (start < end) {
            int mid = (start + end + 1) / 2;
            if (mid > num / mid) {
                end = mid - 1;
            }
            else {
                start = mid;
            }
        }
        return start * start == num;
    }
}
