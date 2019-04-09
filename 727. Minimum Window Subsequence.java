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

class Solution {
    
    public String minWindow(String S, String T) {
        int m = T.length();
        int n = S.length();
        // M[i][j] = k means: Substring S[k~j] covers substring T[0~i] in the right order.
        // That is to say, M[i][j] is the start index of the substring.
        int[][] M = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = i; j < n; j++) {
                // if T[i] == S[j], S[k~j-1][j] covers T[0~i-1][i] => S[k~j-1] covers T[0~i-1]
                // if T[i] != S[j], S[k~j-1][j] covers T[0~i] => S[k~j-1] covers T[0~i]
                if (T.charAt(i) == S.charAt(j)) {
                    M[i][j] = (i == 0 ? j : M[i - 1][j - 1]);
                } else {
                    M[i][j] = (j == i ? -1 : M[i][j - 1]); // Since 0 is a valid start, we need to mark invalid start as -1
                }
            }
        }
        
        int min = n + 1;
        int start = 0, end = -1;
        for (int i = m - 1; i < n; i++) {
            if (M[m - 1][i] == -1) {
                continue;
            }
            if (i - M[m - 1][i] + 1 < min) {
                start = M[m - 1][i];
                end = i;
                min = i - M[m - 1][i] + 1;
            }
        }
        return S.substring(start, end + 1);
    }
}
