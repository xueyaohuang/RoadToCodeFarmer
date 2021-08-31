// use a set if want one pass
class Solution {
    public boolean canPermutePalindrome(String s) {
        int count = 0;
        int[] freq = new int[256];
        for (char c : s.toCharArray()) {
            freq[c - '\0']++;  // '\0'是mull
        }
        for (int i : freq) {
            if (i % 2 == 1) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }
}
