public class Solution {
    
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2) {
            return false;
        }
        int[] freq = new int[26];
        for (char c : s1.toCharArray()) {
            freq[c - 'a']++;
        }
        int left = 0;
        int right = 0;
        int count = 0;
        while (right < len2) {
            char cRight = s2.charAt(right);
            if (freq[cRight - 'a'] > 0) {
                count++;
            }
            freq[cRight - 'a']--;
            right++;
            while (count == len1) {
                if (right - left == len1) {
                    return true;
                }
                char cLeft = s2.charAt(left);
                freq[cLeft - 'a']++;
                if (freq[cLeft - 'a'] > 0) {
                    count--;
                }
                left++;
            }
        }
        return false;                
    }
}

public class Solution {
    
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        for (int i = 0; i <= len2 - len1; i++) {
            if (isAnagram(s1, s2.substring(i, i + len1))) {
                return true;
            }
        }
        return false;                
    }
    
    private boolean isAnagram(String s, String t) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            freq[c - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
