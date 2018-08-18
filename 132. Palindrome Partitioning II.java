class Solution {
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] cut = new int[len];
        Arrays.fill(cut, len - 1);
        for (int i = 0; i < len; i++) {
            expend(s, cut, i, i);
            expend(s, cut, i, i + 1);
        }
        return cut[len - 1];
    }
    
    private void expend(String s, int[] cut, int start, int end) {
        for (; start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end); start--, end++) {
            cut[end] = start == 0 ? 0 : Math.min(cut[end], cut[start - 1] + 1);
        }
    }
}
