// This can be solved by two points:

// cut[j] is the minimum of cut[i - 1] + 1 (i <= j), if [i, j] is palindrome.
// If [i, j] is palindrome, [i + 1, j - 1] is palindrome, and c[j] == c[i].

class Solution {
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        boolean[][] isPalindrome = new boolean[len][len];
        int[] cut = new int[len];
        for (int j = 0; j < len; j++) {
            int min = j;
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (i + 1 >= j || isPalindrome[i + 1][j - 1])) {
                    isPalindrome[i][j] = true;
                    min = i == 0 ? 0 : Math.min(min, cut[i - 1] + 1);
                }
            }
            cut[j] = min;
        }
        return cut[len - 1];
    }
}

class Solution {
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] cut = new int[len];
        Arrays.fill(cut, len - 1);
        for (int i = 0; i < len; i++) {
            expend(s, cut, i, i);
            expend(s, cut, i, i + 1);
        }
        return cut[len - 1];
    }
    
    private void expend(String s, int[] cut, int start, int end) {
        for (; start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end); start--, end++) {
            cut[end] = start == 0 ? 0 : Math.min(cut[end], cut[start - 1] + 1);
        }
    }
}
