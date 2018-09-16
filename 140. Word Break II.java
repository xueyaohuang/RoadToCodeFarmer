// 基本思想还是backtrack，但是加入了很多加速的模块。
// 1. canBeBroken，在做backtrack之前，检查s能否被拆分
// 2. maxWordLength，i每次前进的步数最大是maxWordLength，再大不会有那么长的单词了
// 3. List contains是O(n)， set是O(1)，转换成set检查是否存在
// 4. backtrack返回上层递归，要保证sb和进入这层递归时一样，所以需要sb.delete(originalLen, curLen);
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null) {
            return new ArrayList<>();
        }
        
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        int maxWordLength = 0;
        
        for (String str : wordDict) {
            set.add(str);
            maxWordLength = Math.max(maxWordLength, str.length());
        }
        
        if (canBeBroken(s, set, maxWordLength)) {
            backtrack(s, res, new StringBuilder(), 0, set, maxWordLength);
        }
        
        return res;
    }
    
    private void backtrack(String s, List<String> res, StringBuilder sb, int start, Set<String> set, int maxWordLength) {
        if (start == s.length()) {
            int len = sb.length();
            sb.delete(len - 1, len);
            res.add(sb.toString());
            return;
        }
        
        for (int i = start; i < Math.min(s.length(), start + maxWordLength); i++) {
            if (set.contains(s.substring(start, i + 1))) {
                int sbLen = sb.length();
                sb.append(s.substring(start, i + 1)).append(" ");
                backtrack(s, res, sb, i + 1, set, maxWordLength);
                sb.delete(sbLen, sb.length());
            }
        }
    }
    
    private boolean canBeBroken(String s, Set<String> set, int maxWordLength) {
        int len = s.length();
        boolean[] breakable = new boolean[len + 1];
        breakable[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = Math.max(0, i - maxWordLength); j < i; j++) {
                if (breakable[j] && set.contains(s.substring(j, i))) {
                    breakable[i] = true;
                    break;
                }
            }
        }
        return breakable[len];
    }
}
