class Solution {
    public boolean validPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end && s.charAt(start) == s.charAt(end)) {
            start++;
            end--;
        }
        if (start >= end) {
            return true;
        }
        return isPalindrome(s.substring(start, end)) || isPalindrome(s.substring(start + 1, end + 1));
    }
    
    private boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
             if (s.charAt(start) != s.charAt(end)) {
                 return false;
             }
            start++;
            end--;
        }
        return true;
    }
}
