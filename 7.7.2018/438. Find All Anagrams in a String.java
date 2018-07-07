class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() < p.length()) {
            return res;
        }
        int lenS = s.length();
        int lenP = p.length();
        for (int i = 0; i <= lenS - lenP; i++) {
            if (isAnagram(s.substring(i, i + lenP), p)) {
                res.add(i);
            }
        }
        return res;
    }
    private boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
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

