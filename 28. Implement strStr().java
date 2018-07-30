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
public class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (needle.length() > haystack.length() || haystack.length() == 0) return -1;
        char[] ndl = needle.toCharArray();
        char[] hay = haystack.toCharArray();
        int[] pai = new int[ndl.length];
        pai[0] = -1;
        int k = -1;
        for (int i = 1; i < ndl.length; i++) {
            while (k > -1 && ndl[k + 1] != ndl[i]) {
                k = pai[k];
            }
            if (ndl[k + 1] == ndl[i]) {
                k++;
            }
            pai[i] = k;

        }
        k = -1;
        for (int i = 0; i < hay.length; i++) {
            while (k > -1 && ndl[k + 1] != hay[i]) {
                k = pai[k];
            }
            if (ndl[k + 1] == hay[i]) {
                k++;
                if (k == ndl.length - 1) {
                    return i - k;
                }
            }
        }
        return -1;
    }
}
