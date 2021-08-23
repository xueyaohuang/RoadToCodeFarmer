class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if (words.length < 2) {
            return true;
        }
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int minLen = Math.min(w1.length(), w2.length());
            boolean done = false;
            for (int j = 0; j < minLen; j++) {
                int idx1 = order.indexOf(w1.charAt(j));
                int idx2 = order.indexOf(w2.charAt(j));
                if (idx1 < idx2) {
                    done = true;
                    break;
                } else if (idx1 == idx2) {
                    continue;
                } else {
                    return false;
                }
            }
            if (!done && w1.length() > w2.length()) {
                return false;
            }
        }
        return true;
    }
}


class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || words.length < 2) {
            return true;
        }
        for (int i = 0; i < words.length - 1; i++) {
            int minLen = Math.min(words[i].length(), words[i + 1].length());
            for (int j = 0; j < minLen; j++) {
                int idx1 = order.indexOf(words[i].charAt(j));
                int idx2 = order.indexOf(words[i + 1].charAt(j));
                if (j == minLen - 1 && idx1 == idx2 && words[i].length() > words[i + 1].length()) {
                    return false;
                }
                if (idx1 > idx2) {
                    return false;
                } else if (idx1 == idx2) {
                    continue;
                } else {
                    break;
                }
            }
        }
        return true;
    }
}
