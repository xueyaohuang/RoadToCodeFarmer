// 4,6可以跳到0
// 6,8可以跳到1
// 7,9可以跳到2
// 4,8可以跳到3
// 0,3,9可以跳到4
// ...
class Solution {
    public int knightDialer(int n) {
        if (n == 1) {
            return 10;
        }
        int mod = (int)Math.pow(10, 9) + 7;
        long[] count = new long[]{1, 1, 1, 1, 1, 0, 1, 1, 1, 1};
        for (int i = 2; i <= n; i++) {
            long[] temp = new long[10];
            temp[0] = (count[4] + count[6]) % mod;
            temp[1] = (count[6] + count[8]) % mod;
            temp[2] = (count[7] + count[9]) % mod;
            temp[3] = (count[4] + count[8]) % mod;
            temp[4] = (count[0] + count[3] + count[9]) % mod;
            temp[6] = (count[0] + count[1] + count[7]) % mod;
            temp[7] = (count[2] + count[6]) % mod;
            temp[8] = (count[1] + count[3]) % mod;
            temp[9] = (count[2] + count[4]) % mod;
            count = temp;
        }
        int res = 0;
        for (int i = 0; i < 10; i++) {
            res += count[i];
            res %= mod;
        }
        return res;
    }
}
