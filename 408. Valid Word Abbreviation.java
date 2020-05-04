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
public class Solution {
    /**
     * @param word: a non-empty string
     * @param abbr: an abbreviation
     * @return: true if string matches with the given abbr or false
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        // write your code here
        if (abbr == null) {
            return false;
        }
        int idx = 0;
        int count = 0;
        int len1 = word.length();
        int len2 = abbr.length();
        for (int i = 0; i < len2;) {
            if (Character.isDigit(abbr.charAt(i))) {
                if (abbr.charAt(i) == '0') {
                    return false;
                }
                count = 0;
                while (i < len2 && Character.isDigit(abbr.charAt(i))) {
                    count *= 10;
                    count += abbr.charAt(i) - '0';
                    i++;
                }
                idx += count;
                if (idx > len1) {
                    return false;
                }
            } else {
                if (abbr.charAt(i) != word.charAt(idx)) {
                    return false;
                }
                idx++;
                i++;
                if (idx > len1) {
                    return false;
                }
            }
        }
        return idx == len1;
    }
}
