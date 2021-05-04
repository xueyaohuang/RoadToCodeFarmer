// 取决于有多少个因子5，
// 比如126！其中5有一个因子5，10有一个因子5，25有2个因子5，。。。125有三个因子5.
// 一个因子5的数字个数是 n/5, 
// 二个因子5的数字个数是 n/25, 
// 三个因子5的数字个数是 n/125,
// ...
class Solution {
    public int trailingZeroes(int n) {
        int cur = 1;
        int count = 0;
        while (cur < n) {
            cur *=  5;
            count += n / cur;
        }
        return count;
    }
}
