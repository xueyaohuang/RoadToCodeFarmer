// bottom up
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] canBreak = new boolean[len + 1];
        canBreak[0] = true;
        // j是substring的结尾idx+1，i是substring的开头idx+1
        for (int j = 1; j <= len; j++) {
            for (int i = 1; i <= j; i++) {
                if (canBreak[i - 1] && wordDict.contains(s.substring(i - 1, j))) {
                    canBreak[j] = true;
                    break; // can quit early
                }
            }
        }
        return canBreak[len];
    }
}

// 2
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int len = s.length();
        boolean[] res = new boolean[len + 1];
        // ArrayList.contains() 的时间复杂度是O(n)， HashSet.contains() 的时间复杂度是O(1).
        // 如果有大量的检查是否包含操作，最好转换成O(1)的操作。
        Set<String> set = new HashSet<>();
        int min = 0;
        int max = 0;
        for (String str : wordDict) {
            set.add(str);
            min = Math.min(min, str.length());
            max = Math.max(max, str.length());
        }
        res[0] = true;
        for (int i = min; i <= len; i++) {
            // 这种方法j的意思是待检查str的长度
            for (int j = min; j <= max && j <= i; j++) {
                if (res[i - j] && set.contains(s.substring(i - j, i))) {
                    res[i] = true;
                    break;
                }
            }
        }
        return res[len];
    }
}

// top down with cache
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Map<String, Boolean> cache = new HashMap<>();
        return wordBreakHelper(s, set, cache);
    }
    
    private boolean wordBreakHelper(String s, Set<String> set, Map<String, Boolean> cache) {
        if (s.length() == 0) {
            return true;
        }
        if (cache.containsKey(s)) {
            return cache.get(s);
        }
        int i = 0;
        int len = s.length();
        while (i < len) {
            String cur = s.substring(0, i + 1);
            if (set.contains(cur)) {
                if (wordBreakHelper(s.substring(i + 1), set, cache)) {
                    cache.put(s.substring(i + 1), true);
                    return true;
                }
            }
            i++;
        }
        cache.put(s, false);
        return false;
    }
}

// TLE
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        return canBreak(s, dict, 0);
    }
    
    private boolean canBreak(String s, Set<String> dict, int start) {
        if (start == s.length()) {
            return true;
        }
        for (int i = start; i < s.length(); i++) {
            String cur = s.substring(start, i + 1);
            if (dict.contains(cur)) {
                if (canBreak(s, dict, i + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}
