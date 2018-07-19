class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();
        if (lenS > lenT) {
            return isOneEditDistance(t, s);
        }
        if (Math.abs(lenS - lenT) > 1) {
            return false;
        }
        if (s.equals(t)) {
            return false;
        }
        if (lenS == lenT) {
            int count = 0;
            for (int i = 0; i < lenS; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    count++;
                }
                if (count > 1) {
                    return false;
                }
            }
            return true;
        }
        else {
            int start = 0;
            while (start < lenS) {
                if (s.charAt(start) != t.charAt(start)) {
                    break;
                }
                start++;
            }
            return s.substring(start, lenS).equals(t.substring(start + 1, lenT));
        }
    }
}
