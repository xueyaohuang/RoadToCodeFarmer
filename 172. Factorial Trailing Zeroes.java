// 取决于有多少个因子5，
// 比如126！其中5有一个因子5，10有一个因子5，25有2个因子5，。。。125有三个因子5.
class Solution {
    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 0) {
            res += n / 5;
            n /= 5;
        }
        return res;
    }
}
