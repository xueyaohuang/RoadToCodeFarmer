/*
dp[i-1][j] means that the * matches a 1 character (most recent) in S and we can continue to use * to match more previous characters

dp[i][j-1] means that the * matches no character in S

Take Example:
S: xxx
P: xx*

For i=2, j=2:
dp[1][2] means we use the * to match s[2], can we match the remaining sequence in S if we reuse * in P?

dp[2][1] means if we don't use * to match any characters at all, our s[2] would have to match p[1].

IE: the characters before the * in P would have to match characters in S so far. Meaning p[i][j-1].
*/

class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        int lens = s.length(), lenp = p.length();
        boolean[][] canMatch = new boolean[lens + 1][lenp + 1];
        canMatch[0][0] = true;
        
        for (int i = 0; i < lenp; i++) {
            if (p.charAt(i) == '*') {
                canMatch[0][i + 1] = true;
            } else {
                break;
            }
        }
        
        for (int i = 1; i <= lens; i++) {
            for (int j = 1; j <= lenp; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    canMatch[i][j] = canMatch[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    canMatch[i][j] = canMatch[i][j - 1] || canMatch[i - 1][j];
                }
            }
        }
        
        return canMatch[lens][lenp];
    }
}
