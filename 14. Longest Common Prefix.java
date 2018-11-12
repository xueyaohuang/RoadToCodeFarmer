class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int len = strs[0].length();
        for (int i = 0; i < len; i++) {
            String prefix = strs[0].substring(0, i + 1);
            for (int j = 1; j < strs.length; j++) {
                if (!strs[j].startsWith(prefix)) {
                    return strs[0].substring(0, i);
                }
            } 
        }
        return strs[0];
    }
}
