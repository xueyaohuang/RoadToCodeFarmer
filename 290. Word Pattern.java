class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");
        if (pattern.length() != strs.length) {
            return false;
        }
        int len = pattern.length();
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = pattern.charAt(i);
            if (!map.containsKey(c)) {
                if (map.values().contains(strs[i])) {
                    return false;
                }
                map.put(c, strs[i]);
            }
            else {
                if (!strs[i].equals(map.get(c))) {
                    return false;
                }
            }
        }
        return true;
    }
}
