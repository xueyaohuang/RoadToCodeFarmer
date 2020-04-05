class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        Set<String> isSubSeq = new HashSet<>();
        Set<String> isNotSubSeq = new HashSet<>();
        int res = 0;
        for (String w : words) {
            if (isSubSeq.contains(w)) {
                res++;
                continue;
            }
            if (isNotSubSeq.contains(w)) {
                continue;
            }
            if (isSubsquence(w, S)) {
                res++;
                isSubSeq.add(w);
            } else {
                isNotSubSeq.add(w);
            }
        }
        return res;
    }

    private boolean isSubsquence(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        int idx = -1;
        for (char c : s.toCharArray()) {
            idx = t.indexOf(c, idx + 1);
            if (idx == -1) {
                return false;
            }
        }
        return true;
    }
}
