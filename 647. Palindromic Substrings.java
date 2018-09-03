class Solution {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int count = 0;
        for (int i = 0; i < len; i++) {
            count += palindromeFromHere(s, i, i);
            count += palindromeFromHere(s, i, i + 1);
        }
        return count;
    }
    private int palindromeFromHere(String s, int i, int j) {
        int count = 0;
        int len = s.length();
        while (i >= 0 && j < len && s.charAt(i) == s.charAt(j)) {
            count++;
            i--;
            j++;
        }
        return count;
    }
}

class Solution {
    int count = 0;
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        for (int i = 0; i < s.length(); i++) {
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i + 1);
        }
        return count;
    }
    
    private void extendPalindrome(String s, int head, int tail) {
        while (head >= 0 && tail < s.length() && s.charAt(head) == s.charAt(tail)) {
            count++;
            head--;
            tail++;
        }
    }
}
