class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        backtracking(s, res, new StringBuilder(), 0, 0);
        return res;
    }
    
    private void backtracking(String s, List<String> res, StringBuilder sb, int start, int num) {
        if (num == 4) {
            if (sb.length() - 4 == s.length()) {
                int len = sb.length();
                sb.delete(len - 1, len);
                res.add(sb.toString());
            }
            return;
        }
        for (int i = start; i < Math.min(s.length(), start + 3); i++) {
            String cur = s.substring(start, i + 1);
            // 排除010这样的
            if (cur.length() > 1 && cur.charAt(0) == '0') {
                continue;
            }
            if (Integer.valueOf(cur) < 256) {
                int sbLen = sb.length();
                sb.append(s.substring(start, i + 1)).append(".");
                backtracking(s, res, sb, i + 1, num + 1);
                sb.delete(sbLen, sb.length());
            }
        }
    }
}
