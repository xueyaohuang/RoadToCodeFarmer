class Solution {
    public String minWindow(String S, String T) {
        int n = S.length();
        int m = T.length();
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int i = 0; 
        int tindex = 0;
        int len = Integer.MAX_VALUE;
        int start = 0;
        while (i < n) {
            if (t[tindex] == s[i]) {
                tindex++;
            }
            if (tindex == m) {
                tindex--;
                int end = i;
                while (tindex >= 0) {
                    if (t[tindex] == s[i]) {
                        tindex--;
                    }
                    i--;
                }
                tindex++; // tindex归零
                i++;      // i指到最短subsequence的第一个char，必须有这一步，否则会无限循环
                if (end - i + 1 < len) {
                    start = i;
                    len = end - i + 1;
                }
            }
            i++;   // i指到最短subsequence的第二个char
        }
        return len == Integer.MAX_VALUE ? "" : S.substring(start, start + len);
    }
}
