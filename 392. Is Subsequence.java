class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        int lens = s.length();
        int lent = t.length();
        int count = 0;
        
        if (lens == 0) {
            return true;
        }
        
        for (int i = 0; i < lent; i++) {
            if (t.charAt(i) == s.charAt(count)) {
                count++;
            }
            if (count == lens) {
                return true;
            }
        }
        return false;
    }
}

// follow up question
