// O(n^3), as substring is O(n)
class Solution {
    public int longestRepeatingSubstring(String S) {
        Set<String> set = new HashSet<>();
        for (int j = S.length() - 1; j >= 1; j--) {
            set.clear();
            for (int i = 0; i < S.length(); i++) {
                if (i + j > S.length()) {
                    break;
                }
                String str = S.substring(i, i + j);
                if (!set.add(str)) {
                    return j;
                }
            }
        }
        return 0;
    }
}

// slightly better, O(n^2lgn), the above solution does linear search on the max length. we can use binary search (try andd fail).
class Solution {
    public int longestRepeatingSubstring(String S) {
        int start = 0, end = S.length() - 1;
        while (start < end) {
            int mid = (start + end + 1) / 2;
            if (hasRepeatingSubstring(S, mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
    
    private boolean hasRepeatingSubstring(String s, int len) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i + len <= s.length(); i++) {
            if (!set.add(s.substring(i, i + len))) {
                return true;
            }
        }
        return false;
    }
}

// dp, O(n^2), Longest Common Substring (LCS) pattern
// dp[i][j] means end with i-1, end with j-1, what's max length of common substring.
// eg. s=abcbc. dp[3][5]=2 because s[:3]=abc, s[:5]==abcbc, longest common substring is "bc"
class Solution {
    public int longestRepeatingSubstring(String S) {
        int n = S.length();
        int[][] dp = new int[n + 1][n + 1];
        int res = 0;
        for (int i = 1; i <= n; i++) {
            // j必须比i小，因为如果j可以等于i，那么最长的common substring肯定是s[:i]
            for (int j = 1; j < i; j++) {
                if (S.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }
}


// Binary Search and Rabin-Karp (O(nlogn) thanks to the rolling hash), 把hasRepeatingSubstring()从O(n^2)变成O(n)
// https://leetcode.com/problems/longest-repeating-substring/discuss/687670/Java-Time-O(NlogN)-Binary-Search-%2B-Rabin-Karp-.-Detail-explanation
