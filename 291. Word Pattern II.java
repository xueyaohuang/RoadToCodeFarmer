// backtracking
// Time complexity: the problem is more like slicing the string into m pieces. How many slicing ways? C(n^m).
// For each slice, it takes O(n) to validate. So the total complexity is O(n * C(n^m))
class Solution {
    public boolean wordPatternMatch(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        return backtracking(pattern, s, 0, 0, map);
    }
    
    private boolean backtracking(String pattern, String s, int idxp, int idxs, Map<Character, String> map) {
        if (idxp == pattern.length() && idxs == s.length()) {
            return true;
        }
        if (idxp >= pattern.length() || idxs >= s.length()) {
            return false;
        }
        char c = pattern.charAt(idxp);
        if (map.containsKey(c)) {
            String str = map.get(c);
            if (!s.substring(idxs).startsWith(str)) {
                return false;
            }
            // 注意这个return不能少
            return backtracking(pattern, s, idxp + 1, idxs + str.length(), map);
        } else {
            for (int i = idxs; i < s.length(); i++) {
                String cur = s.substring(idxs, i + 1);
                if (map.values().contains(cur)) {
                    continue;
                }
                map.put(c, cur);
                if (backtracking(pattern, s, idxp + 1, i + 1, map)) {
                    return true;
                }
                map.remove(c);
            }
        }
        return false;
    }
}
