![kmp table](https://github.com/xueyaohuang/Leetcode-practice/blob/master/Data%20Structures%20and%20Algorithms/figs/kmp1.png)
![kmp prefix algo](https://github.com/xueyaohuang/Leetcode-practice/blob/master/Data%20Structures%20and%20Algorithms/figs/kmp2.png)

* Search for __substring__ , using standard KMP

```
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
```

* Search for __subsequence__

```
class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }
        int idx = 0;
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(idx) == t.charAt(i)) {
                idx++;
            }
            if (idx == s.length()) {
                return true;
            }
        }
        return false;
    }
}
```
