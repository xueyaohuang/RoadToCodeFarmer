// java contains use brute force, so time complexity is O(n^2).
class Solution {
    public boolean rotateString(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }
}
// using kmp to search substring, so time complexity is O(n).
class Solution {
    public boolean rotateString(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        return kmpContains(A + A, B);
    }
    
    private boolean kmpContains(String haystack, String needle) {
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
        return j == needle.length();
        
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
