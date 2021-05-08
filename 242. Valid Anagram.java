class Solution {
    public boolean isAnagram(String s, String t) {
        if ((s == null && t != null) || (s != null && t == null) || s.length() != t.length()) {
            return false;
        }
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        Arrays.sort(sc);
        Arrays.sort(tc);
        for (int i = 0; i < sc.length; i++) {
            if (sc[i] != tc[i]) {
                return false;
            }
        }
        return true;
    }
}

// unicode follow up https://leetcode.com/problems/valid-anagram/discuss/66533/Java-solution-HashMap-Unicode-Follow-up
class Solution {
    public boolean isAnagram(String s, String t) {
        if ((s == null && t != null) || (s != null && t == null) || s.length() != t.length()) {
            return false;
        }
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                return false;
            }
        }
        return true;
    }
}

