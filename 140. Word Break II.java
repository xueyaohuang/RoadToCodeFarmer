


// backtrack, got TLE.
// backtrack一次只加一个element，但这个题一次可以加一个词，backtrack显然太慢了
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null) {
            return new ArrayList<>();
        }
        int len = s.length();
        Set<String> set = new HashSet(wordDict);
        boolean[][] isValid = new boolean[len][len];
        List<String> res = new ArrayList<>();
        
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (set.contains(s.substring(i, j + 1))) {
                    isValid[i][j] = true;
                }
            }
        }
        
        backtrack(s, res, new StringBuilder(), 0, isValid);
        return res;
        
    }
    
    private void backtrack(String s, List<String> res, StringBuilder sb, int start, boolean[][] isValid) {
        if (start == s.length()) {
            res.add(sb.toString().substring(0, sb.length() - 1));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isValid[start][i]) {
                int sbLen = sb.length();
                sb.append(s.substring(start, i + 1)).append(" ");
                backtrack(s, res, sb, i + 1, isValid);
                sb.setLength(sbLen);
            }
        }
    }
}
