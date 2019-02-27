class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int idx1 = 0, idx2 = 0;
        while (idx1 < word.length() && idx2 < abbr.length()) {
            if (!Character.isDigit(abbr.charAt(idx2))) {
                if (abbr.charAt(idx2) != word.charAt(idx1)) {
                    return false;
                }
                idx1++;
                idx2++;
            } else {
                if (abbr.charAt(idx2) == '0') {
                    return false;
                }
                int jump = 0;
                while (idx2 < abbr.length() && Character.isDigit(abbr.charAt(idx2))) {
                    jump = jump * 10 + abbr.charAt(idx2) - '0';
                    idx2++;
                }
                idx1 += jump;
            }
        }
        return idx1 == word.length() && idx2 == abbr.length();
    }
}
