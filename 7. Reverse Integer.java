class Solution {
    public int reverse(int x) {
        int n = x;
        long res = 0;
        while (n != 0) {
            res = res * 10 + n % 10;
            if (res >= Integer.MAX_VALUE || res <= Integer.MIN_VALUE) {
                return 0;
            }
            n /= 10;
        }
        return (int)res;
    }
}
