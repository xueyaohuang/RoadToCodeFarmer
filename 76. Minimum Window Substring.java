class Solution {
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int lenS = s.length();
        int lenT = t.length();
        int[] freq = new int[256];
        for (char c : t.toCharArray()) {
            freq[c]++;
        }
        int start = 0;
        int head = 0;
        int count = 0;
        int minLen = Integer.MAX_VALUE;
        for (int end = 0; end < lenS; end++) {
            char cend = s.charAt(end);
            if (freq[cend]-- > 0) { // freq[cend]无论如何都会--
                count++;
            }
            // count == lenT保证当前window内包含了所有T的char
            while (count == lenT) {
                char cstart = s.charAt(start);
                if (minLen > end - start + 1) {
                    head = start;
                    minLen = end - head + 1;
                }
                // ++freq[cstart] > 0表示，移走的这个char是t中出现的
                if (++freq[cstart] > 0) { // freq[cend]无论如何都会++，并且是先++再判断
                    count--;
                }
                start++; 
            }
        }
        if (minLen == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(head, head + minLen);
    }
}
