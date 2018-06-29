class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] b = new boolean[s.length() + 1];
        b[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (b[j] == true && wordDict.contains(s.substring(j, i))) {
                    b[i] = true;
                }
            }
        }
        return b[s.length()];
    }
}
