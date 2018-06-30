class Solution {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
             return 0;
        }
        int len = s.length();
        int num = 0;
        for (int i = 0; i < len; i++) {
            num += Math.pow(26, len - 1 - i) * (s.charAt(i) - 'A' + 1);
        }
        return num;
    }
}
