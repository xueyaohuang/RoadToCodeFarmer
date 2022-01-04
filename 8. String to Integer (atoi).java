class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
        long sum = 0;
        int sign = 1;
        if (s.charAt(0) == '-') {
            sign = -1;
            s = s.substring(1);
        } else if (s.charAt(0) == '+') {
            s = s.substring(1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return sign * (int)sum;
            }
            sum = sum * 10 + s.charAt(i) - '0';
            if (sign * sum >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign * sum <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        return sign * (int)sum;
    }
}
