// naive way
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j = 0;
            for (; j < needle.length(); j++) {
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    break;
                }
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}

// kmp algo:
class Solution {
    public int strStr(String haystack, String needle) {
        int[] preSuffix = buildPrefixArray(needle);
        int i = 0;
        int j = 0;
        for (;i < haystack.length() && j < needle.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = preSuffix[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
        }
        return j == needle.length() ? i - j : -1;
    }
    
    private int[] buildPrefixArray(String s) {
        int len = s.length();
        int[] preSuffix = new int[len];
        for (int i = 1, j = 0; i < len; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = preSuffix[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            preSuffix[i] = j;
        }
        return preSuffix;
    }
}
