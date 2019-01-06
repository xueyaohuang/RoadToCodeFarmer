// 实际上就是26进制数。满26进一。
class Solution {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int res = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            res *= 26;
            res += s.charAt(i) - 'A' + 1;
        }
        return res;
    }
}
